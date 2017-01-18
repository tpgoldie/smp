package com.tpg.smp.web.controllers.expectations;

import com.tpg.smp.web.model.UserModel;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

public class HandleInvalidLoginRequestExpectation extends RequestExpectation {
    private final WelcomeExpectedAttribute welcomeExpectedModelAttribute;
    private final LoginErrorExpectedAttribute loginErrorExpectedModelAttribute;

    public HandleInvalidLoginRequestExpectation(ResultActions resultActions,
                                                WelcomeExpectedAttribute welcomeExpectedModelAttribute,
                                                LoginErrorExpectedAttribute loginErrorExpectedModelAttribute) {
        super(resultActions);
        this.welcomeExpectedModelAttribute = welcomeExpectedModelAttribute;
        this.loginErrorExpectedModelAttribute = loginErrorExpectedModelAttribute;
    }

    public void met() throws Exception {
        statusIsOk();
        andViewNameIs("index");
        andForwardedUrlIs("/WEB-INF/views/index.jsp");
        andModelAttribute(welcomeExpectedModelAttribute.getAttributeName())
                .is(welcomeExpectedModelAttribute.getExpectedValue());
        andModelAttribute(loginErrorExpectedModelAttribute.getAttributeName())
                .is(loginErrorExpectedModelAttribute.getExpectedValue());
        andSessionAttribute("userModel").is(new UserModel());
    }

    public static class WelcomeExpectedAttribute extends ExpectedAttribute<String> {
        public WelcomeExpectedAttribute(String expectedValue) {
            super("welcome", expectedValue);
        }
    }

    public static class LoginErrorExpectedAttribute extends ExpectedAttribute<String> {
        public LoginErrorExpectedAttribute(String key, String expectedValue) { super(key, expectedValue); }
    }
}
