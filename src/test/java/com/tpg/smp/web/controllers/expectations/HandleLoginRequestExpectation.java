package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

public class HandleLoginRequestExpectation extends RequestExpectation {
    private final WelcomeExpectedModelAttribute welcomeExpectedModelAttribute;

    public HandleLoginRequestExpectation(ResultActions resultActions, WelcomeExpectedModelAttribute welcomeExpectedModelAttribute) {
        super(resultActions);

        this.welcomeExpectedModelAttribute = welcomeExpectedModelAttribute;
    }

    public void met() throws Exception {
        statusIsOk();
        andViewNameIs("index");
        andForwardedUrlIs("/WEB-INF/views/index.jsp");
        andModelAttribute(welcomeExpectedModelAttribute.getAttributeName())
            .is(welcomeExpectedModelAttribute.getExpectedValue());
    }

    public static class WelcomeExpectedModelAttribute extends ExpectedModelAttribute<String> {
        public WelcomeExpectedModelAttribute(String expectedValue) {
            super("welcome.user", expectedValue);
        }
    }
}
