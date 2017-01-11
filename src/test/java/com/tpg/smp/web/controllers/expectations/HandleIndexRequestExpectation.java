package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HandleIndexRequestExpectation {
    private final ResultActions resultActions;
    private final WelcomeExpectedModelAttribute welcomeExpectedModelAttribute;

    public HandleIndexRequestExpectation(ResultActions resultActions,
                                         WelcomeExpectedModelAttribute welcomeExpectedModelAttribute) {
        this.resultActions = resultActions;
        this.welcomeExpectedModelAttribute = welcomeExpectedModelAttribute;
    }

    public void met() throws Exception {
        statusIsOk();
        andViewNameIs("index");
        andForwardedUrlIs("/WEB-INF/jsp/index.jsp");
        andModelAttribute(welcomeExpectedModelAttribute.getAttributeName())
                .is(welcomeExpectedModelAttribute.getExpectedValue());
    }

    private <T> ModelAttributeMatcher<T> andModelAttribute(String attributeName) {
        return new ModelAttributeMatcher<>(resultActions, attributeName);
    }
    private void statusIsOk() throws Exception {
        resultActions.andExpect(status().isOk());
    }

    private void andViewNameIs(String name) throws Exception {
        resultActions.andExpect(view().name(name));
    }

    private void andForwardedUrlIs(String url) throws Exception {
        resultActions.andExpect(forwardedUrl(url));
    }

    public static class WelcomeExpectedModelAttribute extends ExpectedModelAttribute<String> {
        public WelcomeExpectedModelAttribute(String expectedValue) {
            super("welcome", expectedValue);
        }
    }
}