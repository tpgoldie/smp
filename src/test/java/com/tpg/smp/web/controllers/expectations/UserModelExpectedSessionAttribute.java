package com.tpg.smp.web.controllers.expectations;

import com.tpg.smp.web.model.UserModel;

public class UserModelExpectedSessionAttribute extends ExpectedAttribute<UserModel> {
    public UserModelExpectedSessionAttribute(UserModel expectedValue) {
        super("userModel", expectedValue);
    }
}
