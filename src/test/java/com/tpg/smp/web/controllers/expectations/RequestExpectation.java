package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

abstract class RequestExpectation {
    final ResultActions resultActions;

    RequestExpectation(ResultActions resultActions) {
        this.resultActions = resultActions;
    }

    void statusIsOk() throws Exception {
        resultActions.andExpect(status().isOk());
    }

    void andViewNameIs(String name) throws Exception {
        resultActions.andExpect(view().name(name));
    }

    void andForwardedUrlIs(String url) throws Exception {
        resultActions.andExpect(forwardedUrl(url));
    }

    <T> ModelAttributeMatcher<T> andModelAttribute(String attributeName) {
        return new ModelAttributeMatcher<>(resultActions, attributeName);
    }

    <T> SessionAttributeMatcher<T> andSessionAttribute(String attributeName) {
        return new SessionAttributeMatcher<>(resultActions, attributeName);
    }

    ModelAttributeErrorMatcher andModelAttributeHasFieldError(String attributeName) {
        return new ModelAttributeErrorMatcher(resultActions, attributeName);
    }
}
