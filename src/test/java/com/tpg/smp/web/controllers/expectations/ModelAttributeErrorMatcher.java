package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

class ModelAttributeErrorMatcher {
    private final ResultActions resultActions;
    private final String attributeName;

    ModelAttributeErrorMatcher(ResultActions resultActions, String attributeName) {
        this.resultActions = resultActions;
        this.attributeName = attributeName;
    }

    public void is(String value) throws Exception {
        resultActions.andExpect(model().attributeHasFieldErrors(attributeName, value));
    }
}
