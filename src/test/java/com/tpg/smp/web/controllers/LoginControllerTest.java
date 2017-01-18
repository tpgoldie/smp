package com.tpg.smp.web.controllers;

import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.auth.AuthenticatedUsers;
import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.domain.Student;
import com.tpg.smp.web.context.SmpWebConfig;
import com.tpg.smp.web.controllers.expectations.HandleLoginRequestExpectation;
import com.tpg.smp.web.model.UserModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.google.common.base.Optional.of;
import static java.util.Locale.UK;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest({LoginController.class})
@Profile("unitTest")
public class LoginControllerTest extends BaseControllerTest {
    @Configuration
    @ComponentScan(basePackages = {"com.tpg.smp.web.context"})
    @Import(SmpWebConfig.class)
    static class Config {
        @MockBean
        private AuthenticationService authenticationService;

        @Bean
        public AuthenticationService authenticationService() {
            return authenticationService;
        }
    }

    @Autowired
    private AuthenticationService authenticationService;

    private StudentData studentData = new StudentsData().getStudent(0);

    @Test
    public void loginAuthenticatedUser_validAuthenticatedUser_validUserIsLoggedIn() throws Exception {
        Student authenticatedUser = (Student) studentData.getAuthenticatedUser();

        UserModel userModel = studentData.getUserModel();

        when(authenticationService.authenticateUser(userModel)).thenReturn(of(authenticatedUser));

        ResultActions resultsActions = new PerformLogin(mockMvc, userModel.getUsername(), userModel.getSecureToken()).resultActions();

        HandleLoginRequestExpectation expectation = new HandleLoginRequestExpectation(resultsActions,
                new HandleLoginRequestExpectation.WelcomeExpectedModelAttribute(String.format("Welcome %s", authenticatedUser.getFirstName())));

        verify(authenticationService).authenticateUser(userModel);

        expectation.met();
    }

//    @Test
//    public void handleInvalidUser_invalidUser_invalidUserIsNotLoggedOn() throws Exception {
//        UserModel userModel = new UserModel();
//        userModel.setUsername("rubbish user");
//        userModel.setSecureToken("top1234");
//
//        InvalidUser invalidUser = new InvalidUser(userModel.getUsername(), "is an invalid user");
//
//        when(authenticationService.authenticateUser(userModel)).thenReturn(of(invalidUser));
//
//        ResultActions resultsActions = new PerformLogin(mockMvc, userModel.getUsername(), userModel.getSecureToken()).resultActions();
//
//        HandleInvalidLoginRequestExpectation expectation = new HandleInvalidLoginRequestExpectation(resultsActions,
//                new HandleInvalidLoginRequestExpectation.WelcomeExpectedModelAttribute("Welcome To the University of Warwick"),
//                new HandleInvalidLoginRequestExpectation.LoginErrorExpectedModelAttribute("userModel", "login"));
//
//        verify(authenticationService).authenticateUser(userModel);
//    }

    static class PerformLogin {
        private final ResultActions resultActions;

        PerformLogin(MockMvc mockMvc, String username, String secureToken) throws Exception {
            resultActions = mockMvc.perform(post("/smp/login")
                    .contentType(APPLICATION_FORM_URLENCODED_VALUE)
                    .locale(UK)
                    .param("username", username)
                    .param("secureToken", secureToken)
                    .header("Accept-Language", "en_GB")
                    .sessionAttr("userModel", new UserModel()))
                .andDo(print());
        }

        ResultActions resultActions() { return resultActions; }
    }
}
