package com.tpg.smp.web.controllers.expectations;

abstract class ExpectedModelAttribute<T> {
    private final String attributeName;

    private final T expectedValue;

    ExpectedModelAttribute(String attributeName, T expectedValue) {
        this.attributeName = attributeName;
        this.expectedValue = expectedValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public T getExpectedValue() {
        return expectedValue;
    }
}
