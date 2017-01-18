package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

public class SessionAttributeMatcher<T> {
    final ResultActions resultActions;
    final String attributeName;

    public SessionAttributeMatcher(ResultActions resultActions, String attributeName) {
        this.resultActions = resultActions;
        this.attributeName = attributeName;
    }

    public void is(T value) throws Exception {
        resultActions.andExpect(request().sessionAttribute(attributeName, org.hamcrest.Matchers.is(value)));
    }
}
