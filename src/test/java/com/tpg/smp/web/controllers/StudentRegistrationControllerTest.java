package com.tpg.smp.web.controllers;

import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.data.PasswordGenerator;
import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.domain.*;
import com.tpg.smp.services.Success;
import com.tpg.smp.services.registration.StudentRegistrationModel;
import com.tpg.smp.services.registration.StudentRegistrationService;
import com.tpg.smp.web.context.ContentNegotiation;
import com.tpg.smp.web.context.SmpWebConfig;
import com.tpg.smp.web.controllers.expectations.HandleStudentRegistrationRequestExpectation;
import com.tpg.smp.web.controllers.expectations.UserModelExpectedSessionAttribute;
import com.tpg.smp.web.controllers.forms.StudentRegistrationForm;
import com.tpg.smp.web.model.UserModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.google.common.base.Optional.of;
import static com.tpg.smp.domain.Country.UnitedKingdom;
import static com.tpg.smp.domain.IdentityType.BritishDrivingLicence;
import static com.tpg.smp.domain.IdentityType.Passport;
import static java.util.Arrays.asList;
import static java.util.Locale.UK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ContextConfiguration(classes = {StudentRegistrationControllerTest.Config.class})
public class StudentRegistrationControllerTest extends BaseControllerTest {
    @Configuration
    @ComponentScan(basePackages = {"com.tpg.smp.web.context"})
    @Import({ContentNegotiation.class, SmpWebConfig.class})
    static class Config {
        @Autowired
        private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

        @MockBean
        private StudentRegistrationService studentRegistrationService;

        @Bean
        public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() { return jackson2HttpMessageConverter; }

        @Bean
        public PasswordGenerator passwordGenerator() {
            return new PasswordGenerator();
        }
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");

    private static DateTime Now = new DateTime();

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private StudentRegistrationService studentRegistrationService;

    private StudentRegistrationFormBuilder formBuilder = new StudentRegistrationFormBuilder();

    private StudentData studentData = new StudentsData().getStudent(0);

    @Test
    public void handleNewStudentRequest_newStudentRequest_newStudentRegistered() throws Exception {
        UserModel userModel = studentData.getUserModel();

        formBuilder.name(studentData.getDomainModel().getFirstName(), studentData.getDomainModel().getLastName())
            .userModel(userModel)
            .dateOfBirth(DATE_TIME_FORMATTER.parseDateTime("13/04/1997"))
            .dateOfRegistration(Now)
            .address("123 Surrey Street", "Croydon", "Surrey", UnitedKingdom, "CR0 7DD")
            .contactDetails("09632127748", "020864594983")
            .identityDetails(asList(
                new StudentRegistrationFormBuilder.IdHolder(Passport, "BNM-UIO-MIDAN-29304"),
                new StudentRegistrationFormBuilder.IdHolder(BritishDrivingLicence, "HJK-TIO-I2347289")
            )
        );


        StudentRegistrationForm form = formBuilder.build();

        Student student = (Student) studentData.getDomainModel();

        when(authenticationService.authenticateUser(userModel)).thenReturn(of(student));

        Success success = new Success(String.format("%s %s registered.", student.getFirstName(), student.getLastName()));

        when(studentRegistrationService.registerStudent(any(StudentRegistrationModel.class))).thenReturn(success);

        ResultActions resultsAction = new PerformRegistration(mockMvc, jackson2HttpMessageConverter, userModel, form).resultActions();

        StudentRegistrationModel registrationModel = new StudentRegistrationModel(form);

        HandleStudentRegistrationRequestExpectation expectation = new HandleStudentRegistrationRequestExpectation(resultsAction,
            new HandleStudentRegistrationRequestExpectation.SuccessfulRegistrationMessageExpectedAttribute("You're student registration has been successful."),
            new HandleStudentRegistrationRequestExpectation.StudentRegistrationModelExpectedAttribute(registrationModel),
            new HandleStudentRegistrationRequestExpectation.StudentRegistrationServiceVerification(form, studentRegistrationService),
            new UserModelExpectedSessionAttribute(userModel)
        );

        expectation.met();

        verify(authenticationService).authenticateUser(userModel);
    }

    static class PerformRegistration {
        private static final MediaType contentType = new MediaType(APPLICATION_JSON.getType(),
                APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

        private final ResultActions resultActions;

        PerformRegistration(MockMvc mockMvc, MappingJackson2HttpMessageConverter jsonHandler, UserModel userModel, StudentRegistrationForm registrationForm) throws Exception {
            String jsonOutput = json(jsonHandler, registrationForm);

            resultActions = mockMvc.perform(post("/smp/student/register")
                .content(jsonOutput)
                .contentType(contentType)
                .accept(contentType)
                .locale(UK)
                .header("Accept-Language", "en_GB")
                .sessionAttr("userModel", userModel))
                .andDo(print());
        }

        ResultActions resultActions() { return resultActions; }

        private String json(MappingJackson2HttpMessageConverter jsonHandler, StudentRegistrationForm form) throws IOException {
            return jsonHandler.getObjectMapper().writeValueAsString(form);
        }
    }
}
