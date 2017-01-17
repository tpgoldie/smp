package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.data.TeachingStaffMemberData;
import com.tpg.smp.data.TeachingStaffMembersData;
import com.tpg.smp.domain.Student;
import com.tpg.smp.domain.TeachingStaffMember;
import com.tpg.smp.persistence.entities.*;
import com.tpg.smp.persistence.repositories.StudentsQueryRepository;
import com.tpg.smp.persistence.repositories.TeachingStaffMembersQueryRepository;
import com.tpg.smp.persistence.repositories.UsersQueryRepository;
import com.tpg.smp.web.model.UserModel;
import com.tpg.smp.web.model.UserModels;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
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

        @MockBean
        private TeachingStaffMembersQueryRepository teachingStaffMembersQueryRepository;

        @Bean
        public UsersQueryService userQueryService() { return new UsersQueryHandler(usersQueryRepository, studentsQueryRepository, teachingStaffMembersQueryRepository); }
    }

    @Autowired
    private UsersQueryService usersQueryService;

    @Autowired
    private UsersQueryRepository usersQueryRepository;

    @Autowired
    private StudentsQueryRepository studentsQueryRepository;

    @Autowired
    private TeachingStaffMembersQueryRepository teachingStaffMembersQueryRepository;


    private UserModels userModels = new UserModels();

    private UserEntities userEntities =  new UserEntities();

    private StudentEntities studentEntities = new StudentEntities();

    @Test
    public void findStudentByUsernameAndPassword_userDetails_studentToBeFound() {
        StudentData testStudent = new StudentsData().getTestStudent(0);

        UserModel userModel = testStudent.getUserModel();
        UserEntity userEntity = testStudent.getUserEntity();
        StudentEntity studentEntity = testStudent.getStudentEntity();

        userEntity.setPersonId(studentEntity.getId());

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
    public void findTeachingStaffMemberByUsernameAndPassword_userDetails_staffMemberToBeFound() {
        TeachingStaffMemberData testTeachingStaffMember = new TeachingStaffMembersData().getTestTeachingStaffMember(0);

        UserModel userModel = testTeachingStaffMember.getUserModel();
        UserEntity userEntity = testTeachingStaffMember.getUserEntity();
        TeachingStaffMemberEntity teachingStaffMemberEntity = testTeachingStaffMember.getTeachingStaffMemberEntity();

        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(of(userEntity));

        when(studentsQueryRepository.findById(userEntity.getPersonId()))
            .thenReturn(absent());

        when(teachingStaffMembersQueryRepository.findById(userEntity.getPersonId()))
            .thenReturn(of(teachingStaffMemberEntity));

        TeachingStaffMember actual = (TeachingStaffMember) usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken()).get();

        new AssertTeachingStaffMember(actual).matches(userEntity, teachingStaffMemberEntity);

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository).findById(userEntity.getPersonId());

        verify(teachingStaffMembersQueryRepository).findById(userEntity.getPersonId());
    }

    @Test
    public void handleNoMatchingUser_nonExistentUser_expectNothingFound() {
        UserModel userModel = new UserModel();
        userModel.setUsername("another");
        userModel.setSecureToken("asdfasfd");

        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(absent());

        Optional<UserDetails> actual = usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken());

        assertThat(actual, is(absent()));

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository, never()).findById(anyLong());

        verify(teachingStaffMembersQueryRepository, never()).findById(anyLong());
    }
}
