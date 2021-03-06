package com.tpg.smp.web.controllers;

import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.domain.Student;
import com.tpg.smp.web.controllers.expectations.HandleInvalidLoginRequestExpectation;
import com.tpg.smp.web.controllers.expectations.HandleLoginRequestExpectation;
import com.tpg.smp.web.controllers.expectations.UserModelExpectedSessionAttribute;
import com.tpg.smp.web.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static java.util.Locale.UK;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class LoginControllerTest extends BaseControllerTest {
    private StudentData studentData = new StudentsData().getStudent(0);

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void loginAuthenticatedUser_validAuthenticatedUser_validUserIsLoggedIn() throws Exception {
        Student authenticatedUser = (Student) studentData.getAuthenticatedUser();

        UserModel userModel = studentData.getUserModel();

        when(authenticationService.authenticateUser(userModel)).thenReturn(of(authenticatedUser));

        ResultActions resultsActions = new PerformLogin(mockMvc, userModel.getUsername(), userModel.getSecureToken()).resultActions();

        HandleLoginRequestExpectation expectation = new HandleLoginRequestExpectation(resultsActions,
            new HandleLoginRequestExpectation.WelcomeExpectedAttribute(String.format("Welcome %s", authenticatedUser.getFirstName())),
            new UserModelExpectedSessionAttribute(userModel));

        verify(authenticationService).authenticateUser(userModel);

        expectation.met();
    }

    @Test
    public void handleInvalidUser_invalidUser_invalidUserIsNotLoggedOn() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setUsername("rubbish user");
        userModel.setSecureToken("top1234");

        when(authenticationService.authenticateUser(userModel)).thenReturn(absent());

        ResultActions resultsActions = new PerformLogin(mockMvc, userModel.getUsername(), userModel.getSecureToken()).resultActions();

        HandleInvalidLoginRequestExpectation expectation = new HandleInvalidLoginRequestExpectation(resultsActions,
                new HandleInvalidLoginRequestExpectation.WelcomeExpectedAttribute("Welcome To the University of Warwick"),
                new HandleInvalidLoginRequestExpectation.LoginErrorExpectedAttribute("loginError", "Login failed. No match found."));

        expectation.met();

        verify(authenticationService).authenticateUser(userModel);
    }

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
