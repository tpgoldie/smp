package com.tpg.smp.web.controllers.validation;

import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.domain.GenderType;
import com.tpg.smp.domain.Student;
import com.tpg.smp.services.InformationRetrievalService;
import com.tpg.smp.services.conversion.ToDateTimeConverter;
import com.tpg.smp.services.registration.StudentRegistrationModel;
import com.tpg.smp.services.registration.StudentRegistrationService;
import com.tpg.smp.web.controllers.BaseControllerTest;
import com.tpg.smp.web.controllers.ControllerTestConfig;
import com.tpg.smp.web.controllers.StudentRegistrationFormBuilder;
import com.tpg.smp.web.controllers.actions.PerformStudentRegistration;
import com.tpg.smp.web.controllers.expectations.HandleStudentRegistrationRequestExpectation;
import com.tpg.smp.web.controllers.expectations.HandleStudentRegistrationRequestFailedExpectation;
import com.tpg.smp.web.controllers.expectations.UserModelExpectedSessionAttribute;
import com.tpg.smp.web.controllers.forms.StudentRegistrationForm;
import com.tpg.smp.web.model.UserModel;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.tpg.smp.domain.Country.UnitedKingdom;
import static com.tpg.smp.domain.GenderType.Male;
import static com.tpg.smp.domain.IdentityType.BritishDrivingLicence;
import static com.tpg.smp.domain.IdentityType.Passport;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ControllerTestConfig.class})
public class StudentRegistrationValidationTest extends BaseControllerTest {
    private static final DateTime DATE_OF_BIRTH = new ToDateTimeConverter().convert("13/04/1997");

    private static final List<GenderType> GENDERS = GenderType.TypedValues().stream().collect(Collectors.toList());

    @Autowired
    private StudentRegistrationService studentRegistrationService;

    @Autowired
    private InformationRetrievalService informationRetrievalService;

    private StudentRegistrationFormBuilder formBuilder = new StudentRegistrationFormBuilder();

    private StudentData studentData = new StudentsData().getStudent(0);

    private UserModel userModel = studentData.getUserModel();

    private Student student = (Student) studentData.getDomainModel();

    @Before
    public void setUp() {
        super.setUp();

        reset(authenticationService, studentRegistrationService, informationRetrievalService);
    }

    @Test
    public void handleMissingIdentityDetailsValidation_noIdentityDetails_noIdentityDetailsInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .emailAddress("abc@google.com")
            .identityDetails(emptyList())
            .build();

        invalidateMissingDetail(registrationForm, "identityDetails", "identity.details.missing");
    }

    @Test
    public void handleMissingEmailAddress_missingEmailAddress_missingEmailAddressInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "contactDetails.mainEmailAddress", "main.emailaddress.missing");
    }

    @Test
    public void handleMissingUserName_missingUsername_missingUsernameInvalidated() throws Exception {
        userModel.setUsername("");

        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .emailAddress("abc@google.com")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "userModel.username", "usermodel.invalid");
    }

    @Test
    public void handleInvalidUserNameLength_missingInvalidUserNameLength_missingInvalidUserNameLengthInvalidated() throws Exception {
        userModel.setUsername("abcdefghijk");

        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .emailAddress("abc@google.com")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "userModel.username", "usermodel.invalid");
    }

    @Test
    public void handleMissingSecureToken_missingSecureToken_missingSecureTokenInvalidated() throws Exception {
        userModel.setSecureToken("");

        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .emailAddress("abc@google.com")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "userModel.secureToken", "usermodel.invalid");
    }

    @Test
    public void handleInvalidUserTokenLength_missingInvalidUserNameLength_missingInvalidUserTokenLengthInvalidated() throws Exception {
        userModel.setSecureToken("abcdefg");

        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .userModel(userModel)
            .gender(Male)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .emailAddress("abc@google.com")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm,  "userModel.secureToken", "usermodel.invalid");
    }

    @Test
    public void handleMissingDateOfBirth_missingDateOfBirth_missingDateOfBirthInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .userModel(userModel)
            .gender(Male)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .emailAddress("abc@google.com")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "dateOfBirth", "dateofbirth.missing");
    }

    @Test
    public void handleDateOfBirthInvalidFormat_dateOfBirthInvalidFormat_dateOfBirthInvalidFormatInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .gender(Male)
            .userModel(userModel)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        registrationForm.getContactDetails().setMainEmailAddress("ajkl4343");

        invalidateMissingDetail(registrationForm, "dateOfBirth", "dateofbirth.invalid.format");
    }

    @Test
    public void handleMissingContactNumber_missingContactDetails_missingContactDetailsInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .emailAddress("abc@google.com")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "contactDetails.mobileNumber", "contact.number.missing");
    }

    @Test
    public void handleMissingFirstName_missingMissingFirstName_missingFirstNameInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name("", "Smith")
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .emailAddress("abc@google.com")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "name.firstName", "firstname.missing");
    }

    @Test
    public void handleFirstNameMaxSize_firstNameMaxSize_firstNameMaxSizeInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name("aaaabbbbccccddddeeeef", "Smith")
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .emailAddress("abc@google.com")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "name.firstName", "firstname.max.size");
    }

    @Test
    public void handleMissingLastName_missingMissingLastName_missingLastNameInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name("Andy", "")
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .emailAddress("abc@google.com")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "name.lastName", "lastname.missing");
    }

    @Test
    public void handleMissingPostCode_missingMissingPostCode_missingPostCodeInvalidated() throws Exception {
        StudentRegistrationForm registrationForm = formBuilder
            .name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .gender(Male)
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "")
            .emailAddress("abc@google.com")
            .telephoneNumber("020864594983")
            .mobileNumber("09632127748")
            .identityDetails(
                asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            ).build();

        invalidateMissingDetail(registrationForm, "address.postCode", "postcode.missing");
    }

    private void invalidateMissingDetail(StudentRegistrationForm registrationForm, String fieldName, String errorMessageKey) throws Exception {
        when(authenticationService.authenticateUser(userModel)).thenReturn(of(student));

        when(informationRetrievalService.loadCountries()).thenReturn(countriesService.findAll());

        when(informationRetrievalService.loadGenders()).thenReturn(GENDERS);

        ResultActions resultsAction = new PerformStudentRegistration(mockMvc, jackson2HttpMessageConverter, userModel, registrationForm).resultActions();

        HandleStudentRegistrationRequestFailedExpectation expectation = new HandleStudentRegistrationRequestFailedExpectation(
            resultsAction,
            new HandleStudentRegistrationRequestFailedExpectation.RegistrationFailedMessageExpectedAttribute("Your student registration has failed."),
            new HandleStudentRegistrationRequestExpectation.StudentRegistrationModelCountriesExpectedAttribute(countriesService.findAll()),
            new HandleStudentRegistrationRequestExpectation.StudentRegistrationModelGendersExpectedAttribute(GENDERS),
            absent(),
            of(new HandleStudentRegistrationRequestFailedExpectation.StudentRegistrationFormExpectedErrorAttribute(resultsAction,
                    fieldName, errorMessageKey)),
            absent(),
            new UserModelExpectedSessionAttribute(userModel));

        expectation.met();

        verify(studentRegistrationService, never()).registerStudent(any(StudentRegistrationModel.class));

        verify(informationRetrievalService).loadCountries();

        verify(informationRetrievalService).loadGenders();
    }
}
