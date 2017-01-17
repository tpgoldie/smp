package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HandleIndexRequestExpectation extends RequestExpectation {
    private final WelcomeExpectedModelAttribute welcomeExpectedModelAttribute;
    private final LoginExpectedModelAttribute loginExpectedModelAttribute;

    public HandleIndexRequestExpectation(ResultActions resultActions,
                                         WelcomeExpectedModelAttribute welcomeExpectedModelAttribute,
                                         LoginExpectedModelAttribute loginExpectedModelAttribute) {
        super(resultActions);
        this.welcomeExpectedModelAttribute = welcomeExpectedModelAttribute;
        this.loginExpectedModelAttribute = loginExpectedModelAttribute;
    }

    public void met() throws Exception {
        statusIsOk();
        andViewNameIs("index");
        andForwardedUrlIs("/WEB-INF/views/index.jsp");
        andModelAttribute(welcomeExpectedModelAttribute.getAttributeName())
                .is(welcomeExpectedModelAttribute.getExpectedValue());
        andModelAttribute(loginExpectedModelAttribute.getAttributeName())
                .is(loginExpectedModelAttribute.getExpectedValue());
    }

    public static class WelcomeExpectedModelAttribute extends ExpectedModelAttribute<String> {
        public WelcomeExpectedModelAttribute(String expectedValue) {
            super("welcome", expectedValue);
        }
    }

    public static class LoginExpectedModelAttribute extends ExpectedModelAttribute<String> {
        public LoginExpectedModelAttribute(String expectedValue) {
            super("login", expectedValue);
        }
    }
}
