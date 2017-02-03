package com.tpg.smp.services;

import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.data.UsersData;
import com.tpg.smp.domain.ContactDetailType;
import com.tpg.smp.domain.GenderType;
import com.tpg.smp.persistence.entities.*;
import com.tpg.smp.persistence.repositories.*;
import com.tpg.smp.services.conversion.ToDateTimeConverter;
import com.tpg.smp.services.registration.StudentNumberGenerator;
import com.tpg.smp.services.registration.StudentRegistrationModel;
import com.tpg.smp.services.registration.StudentRegistrationService;
import com.tpg.smp.web.controllers.StudentRegistrationFormBuilder;
import com.tpg.smp.web.model.UserModel;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Optional.of;
import static com.tpg.smp.domain.ContactDetailType.MobileNumber;
import static com.tpg.smp.domain.ContactDetailType.TelephoneNumber;
import static com.tpg.smp.domain.Country.UnitedKingdom;
import static com.tpg.smp.domain.IdentityType.BritishDrivingLicence;
import static com.tpg.smp.domain.IdentityType.Passport;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StudentRegistrationServiceTest {
    static final DateTime DATE_OF_BIRTH = new ToDateTimeConverter().convert("13/04/1997");

    static final DateTime DATE_OF_REGISTRATION = new DateTime();

    @Configuration
    @ComponentScan("com.tpg.smp.services.registration")
    static class Config {
        @MockBean
        private PersonManagementRepository personManagementRepository;

        @MockBean
        private StudentManagementRepository studentManagementRepository;

        @MockBean
        private UsersQueryRepository usersQueryRepository;

        @MockBean
        private StudentNumberGenerator studentNumberGenerator;

        @Bean
        public PersonManagementRepository personManagementRepository() { return personManagementRepository; }

        @Bean
        public StudentManagementRepository studentManagementRepository() { return studentManagementRepository; }

        @Bean
        public ContactsManagementRepository contactsManagementRepository() {
            return new StubbedContactsManagementRepository();
        }

        @Bean
        public UsersQueryRepository usersQueryRepository() { return usersQueryRepository; }

        @Bean
        public StudentNumberGenerator studentNumberGenerator() { return studentNumberGenerator; }
    }

    @Autowired
    private PersonManagementRepository personManagementRepository;

    @Autowired
    private StudentManagementRepository studentManagementRepository;

    @Autowired
    private ContactsManagementRepository contactsManagementRepository;

    @Autowired
    private UsersQueryRepository usersQueryRepository;

    @Autowired
    private StudentRegistrationService studentRegistrationService;

    @Autowired
    private StudentNumberGenerator studentNumberGenerator;

    private StudentRegistrationFormBuilder formBuilder = new StudentRegistrationFormBuilder();

    private StudentData studentData = new StudentsData().getStudent(0);

    private UserModel userModel = studentData.getUserModel();

    private UserEntity userEntity = new UsersData().findByUsername(userModel.getUsername());

    @Before
    public void setUp() {
        formBuilder.name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .userModel(userModel)
            .gender(GenderType.Male)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .emailAddress("abc@google.com")
            .identityDetails(asList(
                new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
            ));

    }

    @Test
    public void saveStudentRegistration_studentRegistration_studentRegistrationSaved() {
        StudentRegistrationModel studentRegistrationModel = new StudentRegistrationModel(formBuilder.build());

        when(usersQueryRepository.findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken()))
            .thenReturn(of(userEntity));

        String studentNumber = "XBC-273849";
        when(studentNumberGenerator.generateStudentNumber()).thenReturn(studentNumber);

        studentRegistrationService.registerStudent(studentRegistrationModel);

        assertPersonDetails(studentRegistrationModel);

        assertStudentDetails(studentNumber);

        assertContactNumber(studentRegistrationModel.getContactDetails().getTelephoneNumber(), TelephoneNumber);

        assertContactNumber(studentRegistrationModel.getContactDetails().getMobileNumber(), MobileNumber);
    }

    private void assertContactNumber(String details, ContactDetailType contactDetailType) {
        StubbedContactsManagementRepository stubbedContactsManagementRepository = ((StubbedContactsManagementRepository) contactsManagementRepository);
        ContactDetailsEntity foundContactDetails = stubbedContactsManagementRepository.get(contactDetailType).get();

        assertThat(foundContactDetails, hasProperty("detail", is(details)));
        assertThat(foundContactDetails, hasProperty("detailType", is(contactDetailType)));
    }

    private void assertPersonDetails(StudentRegistrationModel studentRegistrationModel) {
        ArgumentCaptor<PersonEntity> personEntityArgumentCaptor = ArgumentCaptor.forClass(PersonEntity.class);

        verify(personManagementRepository).save(personEntityArgumentCaptor.capture());

        PersonEntity personEntity = personEntityArgumentCaptor.getValue();

        assertThat(personEntity.getName(), hasProperty("firstName", is(studentRegistrationModel.getName().getFirstName())));

        assertThat(personEntity.getName(), hasProperty("lastName", is(studentRegistrationModel.getName().getLastName())));

        assertThat(personEntity.getUser(), hasProperty("username", is(studentRegistrationModel.getUserModel().getUsername())));
    }

    private void assertStudentDetails(String studentNumber) {
        ArgumentCaptor<StudentEntity> studentEntityArgumentCaptor = ArgumentCaptor.forClass(StudentEntity.class);

        verify(usersQueryRepository).findByUsernameAndSecureToken(userModel.getUsername(), userModel.getSecureToken());

        verify(studentManagementRepository).save(studentEntityArgumentCaptor.capture());

        verify(studentNumberGenerator).generateStudentNumber();

        StudentEntity studentEntity = studentEntityArgumentCaptor.getValue();

        assertThat(studentEntity.getName(), hasProperty("firstName", is(studentData.getDomainModel().getFirstName())));

        assertThat(studentEntity.getName(), hasProperty("lastName", is(studentData.getDomainModel().getLastName())));

        assertThat(studentEntity.getGender(), hasProperty("name", is("Male")));

        assertThat(studentEntity, hasProperty("studentNumber", is(studentNumber)));
    }
}
