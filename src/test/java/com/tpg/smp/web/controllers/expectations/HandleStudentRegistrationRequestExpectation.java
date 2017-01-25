package com.tpg.smp.web.controllers.expectations;

import com.tpg.smp.services.conversion.FromDateTimeConverter;
import com.tpg.smp.services.registration.StudentRegistrationModel;
import com.tpg.smp.services.registration.StudentRegistrationService;
import com.tpg.smp.web.controllers.forms.StudentRegistrationForm;
import org.mockito.ArgumentCaptor;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

public class HandleStudentRegistrationRequestExpectation extends RequestExpectation {
    private final SuccessfulRegistrationMessageExpectedAttribute successfulRegistrationMessageExpectedAttribute;
    private final StudentRegistrationModelExpectedAttribute studentRegistrationModelExpectedAttribute;
    private final StudentRegistrationServiceVerification studentRegistrationServiceVerification;
    private final UserModelExpectedSessionAttribute userModelExpectedSessionAttribute;

    public HandleStudentRegistrationRequestExpectation(ResultActions resultActions,
                                                       SuccessfulRegistrationMessageExpectedAttribute successfulRegistrationMessageExpectedAttribute,
                                                       StudentRegistrationModelExpectedAttribute studentRegistrationModelExpectedAttribute,
                                                       StudentRegistrationServiceVerification studentRegistrationServiceVerification,
                                                       UserModelExpectedSessionAttribute userModelExpectedSessionAttribute) {
        super(resultActions);

        this.successfulRegistrationMessageExpectedAttribute = successfulRegistrationMessageExpectedAttribute;
        this.studentRegistrationModelExpectedAttribute = studentRegistrationModelExpectedAttribute;
        this.studentRegistrationServiceVerification = studentRegistrationServiceVerification;
        this.userModelExpectedSessionAttribute = userModelExpectedSessionAttribute;
    }

    public void met() throws Exception {
        statusIsOk();

        andViewNameIs("student/registration/success");

        andForwardedUrlIs("/WEB-INF/views/student/registration/success.jsp");

        andModelAttribute(successfulRegistrationMessageExpectedAttribute.getAttributeName())
                .is(successfulRegistrationMessageExpectedAttribute.getExpectedValue());

        andModelAttribute(studentRegistrationModelExpectedAttribute.getAttributeName())
                .is(studentRegistrationModelExpectedAttribute.getExpectedValue());

        andSessionAttribute(userModelExpectedSessionAttribute.getAttributeName())
                .is(userModelExpectedSessionAttribute.getExpectedValue());

        studentRegistrationServiceVerification.met();
    }

    public static class SuccessfulRegistrationMessageExpectedAttribute extends ExpectedAttribute<String> {
        public SuccessfulRegistrationMessageExpectedAttribute(String expectedValue) {
            super("studentRegistrationSuccess", expectedValue);
        }
    }

    public static class StudentRegistrationModelExpectedAttribute extends ExpectedAttribute<StudentRegistrationModel> {
        public StudentRegistrationModelExpectedAttribute(StudentRegistrationModel registrationModel) {
            super("studentRegistrationModel", registrationModel);
        }
    }

    public static class StudentRegistrationServiceVerification {
        private StudentRegistrationForm registrationForm;
        private StudentRegistrationService studentRegistrationService;

        public StudentRegistrationServiceVerification(StudentRegistrationForm registrationForm, StudentRegistrationService studentRegistrationService) {
            this.registrationForm = registrationForm;
            this.studentRegistrationService = studentRegistrationService;
        }

        void met() {
            ArgumentCaptor<StudentRegistrationModel> argumentCaptor = ArgumentCaptor.forClass(StudentRegistrationModel.class);

            verify(studentRegistrationService).registerStudent(argumentCaptor.capture());

            StudentRegistrationModel actual = argumentCaptor.getValue();

            FromDateTimeConverter fromDateTimeConverter = new FromDateTimeConverter();

            assertThat(actual, hasProperty("name", is(registrationForm.getName())));
            assertThat(actual, hasProperty("userModel", is(registrationForm.getUserModel())));

            assertThat(actual, hasProperty("address", is(registrationForm.getAddress())));

            assertThat(actual, hasProperty("contactDetails", is(registrationForm.getContactDetails())));
            assertThat(actual, hasProperty("identityDetails", is(registrationForm.getIdentityDetails())));

            assertThat(fromDateTimeConverter.convert(actual.getDateOfBirth()), is(registrationForm.getDateOfBirth()));
            assertThat(fromDateTimeConverter.convert(actual.getDateOfRegistration()), is(registrationForm.getDateOfRegistration()));
        }
    }
}
