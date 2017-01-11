package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

class ModelAttributeMatcher<T> {
    final ResultActions resultActions;
    final String attributeName;

    ModelAttributeMatcher(ResultActions resultActions, String attributeName) {
        this.resultActions = resultActions;
        this.attributeName = attributeName;
    }

    public void is(T value) throws Exception {
        resultActions.andExpect(model().attribute(attributeName, org.hamcrest.Matchers.is(value)));
    }
}
