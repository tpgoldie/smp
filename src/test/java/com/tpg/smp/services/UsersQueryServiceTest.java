package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.StudentEntities;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntities;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.persistence.repositories.StudentsQueryRepository;
import com.tpg.smp.persistence.repositories.UsersQueryRepository;
import com.tpg.smp.services.context.ServicesConfig;
import com.tpg.smp.web.model.UserModel;
import com.tpg.smp.web.model.UserModels;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UsersQueryServiceTest {
    @Configuration
    static class Config {
        @MockBean
        private UsersQueryRepository usersQueryRepository;

        @MockBean
        private StudentsQueryRepository studentsQueryRepository;

        @Bean
        public UsersQueryService userQueryService() { return new UsersQueryHandler(usersQueryRepository, studentsQueryRepository); }
    }

    @Autowired
    private UsersQueryService usersQueryService;

    @Autowired
    private UsersQueryRepository usersQueryRepository;

    @Autowired
    private StudentsQueryRepository studentsQueryRepository;

    private UserModel userModel = new UserModels().getUserModel(0);

    private UserEntity userEntity =  new UserEntities().getEntity(0);

    private StudentEntity studentEntity = new StudentEntities().getEntity(0);

    @Before
    public void setUp() {
        userEntity.setPersonId(studentEntity.getId());
    }

    @Test
    public void findStudentByUsernameAndPassword_userDetails_studentToBeFound() {
        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(of(userEntity));

        when(studentsQueryRepository.findById(userEntity.getPersonId()))
            .thenReturn(of(studentEntity));

        Student actual = (Student) usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken()).get();

        new AssertStudent(actual).matches(userEntity, studentEntity);

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository).findById(userEntity.getPersonId());
    }

    @Test
    public void handleNoMatchingUser_nonExistentUser_expectNothingFound() {
        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(absent());

        Optional<UserDetails> actual = usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken());

        assertThat(actual, is(absent()));

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository, never()).findById(userEntity.getPersonId());
    }
}
