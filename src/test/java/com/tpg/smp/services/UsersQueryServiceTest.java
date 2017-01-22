package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.data.*;
import com.tpg.smp.domain.AdministrativeStaffMember;
import com.tpg.smp.domain.AlumniMember;
import com.tpg.smp.domain.Student;
import com.tpg.smp.domain.AcademicStaffMember;
import com.tpg.smp.persistence.entities.*;
import com.tpg.smp.persistence.repositories.*;
import com.tpg.smp.web.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
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
        private AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository;

        @MockBean
        private AdministrativeStaffMembersQueryRepository administrativeStaffMembersQueryRepository;

        @MockBean
        private AlumniMembersQueryRepository alumniMembersQueryRepository;

        @Bean
        public UsersQueryService userQueryService() { return new UsersQueryHandler(usersQueryRepository, studentsQueryRepository,
                academicStaffMembersQueryRepository, administrativeStaffMembersQueryRepository, alumniMembersQueryRepository); }
    }

    @Autowired
    private UsersQueryService usersQueryService;

    @Autowired
    private UsersQueryRepository usersQueryRepository;

    @Autowired
    private StudentsQueryRepository studentsQueryRepository;

    @Autowired
    private AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository;

    @Autowired
    private AdministrativeStaffMembersQueryRepository administrativeStaffMembersQueryRepository;

    @Autowired
    private AlumniMembersQueryRepository alumniMembersQueryRepository;

    @Test
    public void findStudentByUsernameAndPassword_userDetails_studentToBeFound() {
        StudentData testStudent = new StudentsData().getStudent(0);

        UserModel userModel = testStudent.getUserModel();
        UserEntity userEntity = testStudent.getUserEntity();
        StudentEntity studentEntity = testStudent.getStudentEntity();

        userEntity.setPerson(studentEntity);

        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(of(userEntity));

        when(studentsQueryRepository.findById(userEntity.getPerson().getId()))
            .thenReturn(of(studentEntity));

        Student actual = (Student) usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken()).get();

        new AssertStudent(actual).matches(userEntity, studentEntity);

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository).findById(userEntity.getPerson().getId());

        verify(academicStaffMembersQueryRepository, never()).findById(userEntity.getPerson().getId());

        verify(administrativeStaffMembersQueryRepository, never()).findById(userEntity.getPerson().getId());

        verify(alumniMembersQueryRepository, never()).findById(userEntity.getPerson().getId());
    }

    @Test
    public void findAcademicStaffMemberByUsernameAndPassword_userDetails_staffMemberToBeFound() {
        AcademicStaffMemberData testTeachingStaffMember = new AcademicStaffMembersData().getAcademicStaffMember(0);

        UserModel userModel = testTeachingStaffMember.getUserModel();
        UserEntity userEntity = testTeachingStaffMember.getUserEntity();
        AcademicStaffMemberEntity academicStaffMemberEntity = testTeachingStaffMember.getAcademicStaffMemberEntity();

        userEntity.setPerson(academicStaffMemberEntity);

        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(of(userEntity));

        when(studentsQueryRepository.findById(userEntity.getPerson().getId()))
            .thenReturn(absent());

        when(academicStaffMembersQueryRepository.findById(userEntity.getPerson().getId()))
            .thenReturn(of(academicStaffMemberEntity));

        when(alumniMembersQueryRepository.findById(userEntity.getPerson().getId()))
                .thenReturn(absent());

        AcademicStaffMember actual = (AcademicStaffMember) usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken()).get();

        new AssertAcademicStaffMember(actual).matches(userEntity, academicStaffMemberEntity);

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository).findById(userEntity.getPerson().getId());

        verify(academicStaffMembersQueryRepository).findById(userEntity.getPerson().getId());

        verify(administrativeStaffMembersQueryRepository, never()).findById(userEntity.getPerson().getId());

        verify(alumniMembersQueryRepository, never()).findById(userEntity.getPerson().getId());
    }

    @Test
    public void findAdministrativeStaffMemberByUsernameAndPassword_userDetails_staffMemberFound() {
        AdministrativeStaffMemberData testAdministrativeStaffMember = new AdministrativeStaffMembersData().getAdministrativeStaffMember(0);

        UserModel userModel = testAdministrativeStaffMember.getUserModel();
        UserEntity userEntity = testAdministrativeStaffMember.getUserEntity();
        AdministrativeStaffMemberEntity administrativeStaffMemberEntity = testAdministrativeStaffMember.getAdministrativeStaffMemberEntity();

        userEntity.setPerson(administrativeStaffMemberEntity);

        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(of(userEntity));

        when(studentsQueryRepository.findById(userEntity.getPerson().getId()))
            .thenReturn(absent());

        when(academicStaffMembersQueryRepository.findById(userEntity.getPerson().getId()))
            .thenReturn(absent());

        when(alumniMembersQueryRepository.findById(userEntity.getPerson().getId()))
            .thenReturn(absent());

        when(administrativeStaffMembersQueryRepository.findById(userEntity.getPerson().getId()))
            .thenReturn(of(administrativeStaffMemberEntity));

        AdministrativeStaffMember actual = (AdministrativeStaffMember) usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken()).get();

        new AssertAdministrativeStaffMember(actual).matches(userEntity, administrativeStaffMemberEntity);

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository).findById(userEntity.getPerson().getId());

        verify(academicStaffMembersQueryRepository).findById(userEntity.getPerson().getId());

        verify(administrativeStaffMembersQueryRepository).findById(userEntity.getPerson().getId());

        verify(alumniMembersQueryRepository, never()).findById(userEntity.getPerson().getId());
    }

    @Test
    public void findAlumniMemberByUsernameAndPassword_userDetails_memberFound() {
        AlumniMemberData testAlumniMember = new AlumniMembersData().getAlumniMember(0);

        UserModel userModel = testAlumniMember.getUserModel();
        UserEntity userEntity = testAlumniMember.getUserEntity();
        AlumniMemberEntity alumniMemberEntity = testAlumniMember.getAlumniMemberEntity();

        userEntity.setPerson(alumniMemberEntity);

        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
                .thenReturn(of(userEntity));

        when(studentsQueryRepository.findById(userEntity.getPerson().getId()))
                .thenReturn(absent());

        when(academicStaffMembersQueryRepository.findById(userEntity.getPerson().getId()))
                .thenReturn(absent());

        when(administrativeStaffMembersQueryRepository.findById(userEntity.getPerson().getId()))
                .thenReturn(absent());

        when(alumniMembersQueryRepository.findById(userEntity.getPerson().getId()))
                .thenReturn(of(alumniMemberEntity));

        AlumniMember actual = (AlumniMember) usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken()).get();

        new AssertAlumniMember(actual).matches(userEntity, alumniMemberEntity);

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository).findById(userEntity.getPerson().getId());

        verify(academicStaffMembersQueryRepository).findById(userEntity.getPerson().getId());

        verify(administrativeStaffMembersQueryRepository).findById(userEntity.getPerson().getId());

        verify(alumniMembersQueryRepository).findById(userEntity.getPerson().getId());
    }

    @Test
    public void handleNoMatchingUser_nonExistentUser_expectNothingFound() {
        UserModel userModel = new UserModel();
        userModel.setUsername("another");
        userModel.setSecureToken("asdfasfd");

        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(absent());

        Optional<AuthenticatedUser> actual = usersQueryService.findUserByUsernameAndPassword(userModel.getUsername(), userModel.getSecureToken());

        assertThat(actual, is(absent()));

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentsQueryRepository, never()).findById(anyLong());

        verify(academicStaffMembersQueryRepository, never()).findById(anyLong());

        verify(administrativeStaffMembersQueryRepository, never()).findById(anyLong());
    }
}
