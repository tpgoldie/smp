package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

public class HandleLoginRequestExpectation extends RequestExpectation {
    private final WelcomeExpectedAttribute welcomeExpectedModelAttribute;
    private final UserModelExpectedSessionAttribute userModelExpectedSessionAttribute;

    public HandleLoginRequestExpectation(ResultActions resultActions, WelcomeExpectedAttribute welcomeExpectedModelAttribute,
                                         UserModelExpectedSessionAttribute userModelExpectedSessionAttribute) {
        super(resultActions);

        this.welcomeExpectedModelAttribute = welcomeExpectedModelAttribute;
        this.userModelExpectedSessionAttribute = userModelExpectedSessionAttribute;
    }

    public void met() throws Exception {
        statusIsOk();
        andViewNameIs("index");
        andForwardedUrlIs("/WEB-INF/views/index.jsp");
        andModelAttribute(welcomeExpectedModelAttribute.getAttributeName())
            .is(welcomeExpectedModelAttribute.getExpectedValue());
        andSessionAttribute(userModelExpectedSessionAttribute.getAttributeName())
            .is(userModelExpectedSessionAttribute.getExpectedValue());
    }

    public static class WelcomeExpectedAttribute extends ExpectedAttribute<String> {
        public WelcomeExpectedAttribute(String expectedValue) {
            super("welcome", expectedValue);
        }
    }
}
