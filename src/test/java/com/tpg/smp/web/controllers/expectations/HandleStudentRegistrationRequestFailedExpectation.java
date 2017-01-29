package com.tpg.smp.web.controllers.expectations;

import com.google.common.base.Optional;
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

public class HandleStudentRegistrationRequestFailedExpectation extends RequestExpectation {
    private final RegistrationFailedMessageExpectedAttribute registrationFailedMessageExpectedAttribute;
    private final HandleStudentRegistrationRequestExpectation.StudentRegistrationModelCountriesExpectedAttribute studentRegistrationModelCountriesExpectedAttribute;
    private final HandleStudentRegistrationRequestExpectation.StudentRegistrationModelGendersExpectedAttribute studentRegistrationModelGendersExpectedAttribute;
    private final Optional<StudentRegistrationModelExpectedAttribute> studentRegistrationModelExpectedAttribute;
    private final Optional<StudentRegistrationFormExpectedErrorAttribute> studentRegistrationFormExpectedErrorAttribute;
    private final Optional<StudentRegistrationServiceVerification> studentRegistrationServiceVerification;
    private final UserModelExpectedSessionAttribute userModelExpectedSessionAttribute;

    public HandleStudentRegistrationRequestFailedExpectation(ResultActions resultActions,
                                                             RegistrationFailedMessageExpectedAttribute registrationFailedMessageExpectedAttribute,
                                                             HandleStudentRegistrationRequestExpectation.StudentRegistrationModelCountriesExpectedAttribute studentRegistrationModelCountriesExpectedAttribute,
                                                             HandleStudentRegistrationRequestExpectation.StudentRegistrationModelGendersExpectedAttribute studentRegistrationModelGendersExpectedAttribute,
                                                             Optional<StudentRegistrationModelExpectedAttribute> studentRegistrationModelExpectedAttribute,
                                                             Optional<StudentRegistrationFormExpectedErrorAttribute> studentRegistrationFormExpectedErrorAttribute,
                                                             Optional<StudentRegistrationServiceVerification> studentRegistrationServiceVerification,
                                                             UserModelExpectedSessionAttribute userModelExpectedSessionAttribute) {
        super(resultActions);

        this.registrationFailedMessageExpectedAttribute = registrationFailedMessageExpectedAttribute;
        this.studentRegistrationModelCountriesExpectedAttribute = studentRegistrationModelCountriesExpectedAttribute;
        this.studentRegistrationModelGendersExpectedAttribute = studentRegistrationModelGendersExpectedAttribute;
        this.studentRegistrationModelExpectedAttribute = studentRegistrationModelExpectedAttribute;
        this.studentRegistrationFormExpectedErrorAttribute = studentRegistrationFormExpectedErrorAttribute;
        this.studentRegistrationServiceVerification = studentRegistrationServiceVerification;
        this.userModelExpectedSessionAttribute = userModelExpectedSessionAttribute;
    }

    public void met() throws Exception {
        statusIsOk();

        andViewNameIs("student/registration/register");

        andForwardedUrlIs("/WEB-INF/views/student/registration/register.jsp");

        andModelAttribute(registrationFailedMessageExpectedAttribute.getAttributeName())
            .is(registrationFailedMessageExpectedAttribute.getExpectedValue());

        if (studentRegistrationModelExpectedAttribute.isPresent()) {
            andModelAttribute(studentRegistrationModelExpectedAttribute.get().getAttributeName())
                .is(studentRegistrationModelExpectedAttribute.get().getExpectedValue());
        }

        andModelAttribute(studentRegistrationModelCountriesExpectedAttribute.getAttributeName())
            .is(studentRegistrationModelCountriesExpectedAttribute.getExpectedValue());

        andModelAttribute(studentRegistrationModelGendersExpectedAttribute.getAttributeName())
            .is(studentRegistrationModelGendersExpectedAttribute.getExpectedValue());

        andSessionAttribute(userModelExpectedSessionAttribute.getAttributeName())
            .is(userModelExpectedSessionAttribute.getExpectedValue());

        if (studentRegistrationFormExpectedErrorAttribute.isPresent()) {
            studentRegistrationFormExpectedErrorAttribute.get().hasError();
        }

        if (studentRegistrationServiceVerification.isPresent()) {
            studentRegistrationServiceVerification.get().met();
        }
    }

    public static class RegistrationFailedMessageExpectedAttribute extends ExpectedAttribute<String> {
        public RegistrationFailedMessageExpectedAttribute(String expectedValue) {
            super("studentRegistrationFailed", expectedValue);
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

    public static class StudentRegistrationFormExpectedErrorAttribute extends ModelAttributeErrorMatcher {
        public StudentRegistrationFormExpectedErrorAttribute(ResultActions resultActions, String attributeName, String msgKey) {
            super(resultActions, "studentRegistrationForm", attributeName, msgKey);
        }
    }
}
