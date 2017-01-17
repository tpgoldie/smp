package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

abstract class RequestExpectation {
    protected final ResultActions resultActions;

    protected RequestExpectation(ResultActions resultActions) {
        this.resultActions = resultActions;
    }

    protected void statusIsOk() throws Exception {
        resultActions.andExpect(status().isOk());
    }

    protected void andViewNameIs(String name) throws Exception {
        resultActions.andExpect(view().name(name));
    }

    protected void andForwardedUrlIs(String url) throws Exception {
        resultActions.andExpect(forwardedUrl(url));
    }

    protected <T> ModelAttributeMatcher<T> andModelAttribute(String attributeName) {
        return new ModelAttributeMatcher<>(resultActions, attributeName);
    }
}
