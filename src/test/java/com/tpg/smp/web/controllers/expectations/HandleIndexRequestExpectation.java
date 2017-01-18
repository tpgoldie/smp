package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

public class HandleIndexRequestExpectation extends RequestExpectation {
    private final WelcomeExpectedAttribute welcomeExpectedModelAttribute;
    private final LoginExpectedAttribute loginExpectedModelAttribute;

    public HandleIndexRequestExpectation(ResultActions resultActions,
                                         WelcomeExpectedAttribute welcomeExpectedModelAttribute,
                                         LoginExpectedAttribute loginExpectedModelAttribute) {
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

    public static class WelcomeExpectedAttribute extends ExpectedAttribute<String> {
        public WelcomeExpectedAttribute(String expectedValue) {
            super("welcome", expectedValue);
        }
    }

    public static class LoginExpectedAttribute extends ExpectedAttribute<String> {
        public LoginExpectedAttribute(String expectedValue) {
            super("login", expectedValue);
        }
    }
}
