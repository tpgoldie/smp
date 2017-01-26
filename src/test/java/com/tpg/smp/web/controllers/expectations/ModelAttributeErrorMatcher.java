package com.tpg.smp.web.controllers.expectations;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

class ModelAttributeErrorMatcher {
    private final ResultActions resultActions;
    private final String objectName;
    private final String fieldName;
    private final String messageKey;

    ModelAttributeErrorMatcher(ResultActions resultActions, String objectName, String fieldName, String messageKey) {
        this.resultActions = resultActions;
        this.objectName = objectName;
        this.fieldName = fieldName;
        this.messageKey = messageKey;
    }

    public void hasError() throws Exception {
        resultActions.andExpect(model().hasErrors());
        resultActions.andExpect(model().errorCount(1));
        resultActions.andExpect(model().attributeHasFieldErrors(objectName, fieldName));
    }
}
