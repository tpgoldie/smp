package com.tpg.smp.web.controllers;

import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.domain.*;
import com.tpg.smp.services.Failure;
import com.tpg.smp.services.Success;
import com.tpg.smp.services.conversion.ToDateTimeConverter;
import com.tpg.smp.services.registration.StudentRegistrationModel;
import com.tpg.smp.services.registration.StudentRegistrationService;
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

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.tpg.smp.domain.Country.UnitedKingdom;
import static com.tpg.smp.domain.IdentityType.BritishDrivingLicence;
import static com.tpg.smp.domain.IdentityType.Passport;
import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ControllerTestConfig.class})
public class StudentRegistrationControllerTest extends BaseControllerTest {
    private static final DateTime DATE_OF_BIRTH = new ToDateTimeConverter().convert("13/04/1997");

    @Autowired
    private StudentRegistrationService studentRegistrationService;

    private StudentRegistrationFormBuilder formBuilder = new StudentRegistrationFormBuilder();

    private StudentData studentData = new StudentsData().getStudent(0);

    private UserModel userModel = studentData.getUserModel();

    @Before
    public void setUp() {
        super.setUp();

        reset(authenticationService, studentRegistrationService);

        formBuilder.name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .userModel(userModel)
            .dateOfBirth(DATE_OF_BIRTH)
            .dateOfRegistration(DATE_OF_REGISTRATION)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .contactDetails("09632127748", "020864594983")
            .emailAddress("abc@google.com")
            .identityDetails(asList(
                    new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                    new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
                )
            );
    }

    @Test
    public void handleNewStudentRequest_newStudentRequest_newStudentRegistered() throws Exception {
        StudentRegistrationForm form = formBuilder.build();

        Student student = (Student) studentData.getDomainModel();

        when(authenticationService.authenticateUser(userModel)).thenReturn(of(student));

        Success success = new Success(String.format("%s %s registered.", student.getFirstName(), student.getLastName()));

        when(studentRegistrationService.registerStudent(any(StudentRegistrationModel.class))).thenReturn(success);

        ResultActions resultsAction = new PerformStudentRegistration(mockMvc, jackson2HttpMessageConverter, userModel, form).resultActions();

        StudentRegistrationModel registrationModel = new StudentRegistrationModel(form);

        HandleStudentRegistrationRequestExpectation expectation = new HandleStudentRegistrationRequestExpectation(resultsAction,
            new HandleStudentRegistrationRequestExpectation.SuccessfulRegistrationMessageExpectedAttribute("Your student registration has been successful."),
            new HandleStudentRegistrationRequestExpectation.StudentRegistrationModelExpectedAttribute(registrationModel),
            new HandleStudentRegistrationRequestExpectation.StudentRegistrationServiceVerification(form, studentRegistrationService),
            new UserModelExpectedSessionAttribute(userModel)
        );

        expectation.met();

        verify(authenticationService).authenticateUser(userModel);
    }

    @Test
    public void handleStudentRegistrationFailure_invalidStudentRegistration_handledStudentRegsitrationFailure() throws Exception {
        StudentRegistrationForm form = formBuilder.build();

        Student student = (Student) studentData.getDomainModel();

        when(authenticationService.authenticateUser(userModel)).thenReturn(of(student));

        Failure failure = new Failure(String.format("%s %s registration failed.", student.getFirstName(), student.getLastName()));

        when(studentRegistrationService.registerStudent(any(StudentRegistrationModel.class))).thenReturn(failure);

        ResultActions resultsAction = new PerformStudentRegistration(mockMvc, jackson2HttpMessageConverter, userModel, form).resultActions();

        StudentRegistrationModel registrationModel = new StudentRegistrationModel(form);

        HandleStudentRegistrationRequestFailedExpectation expectation = new HandleStudentRegistrationRequestFailedExpectation(resultsAction,
                new HandleStudentRegistrationRequestFailedExpectation.RegistrationFailedMessageExpectedAttribute("Your student registration has failed."),
                of(new HandleStudentRegistrationRequestFailedExpectation.StudentRegistrationModelExpectedAttribute(registrationModel)), absent(),
                of(new HandleStudentRegistrationRequestFailedExpectation.StudentRegistrationServiceVerification(form, studentRegistrationService)),
                new UserModelExpectedSessionAttribute(userModel));

        expectation.met();
    }
}
