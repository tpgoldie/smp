package com.tpg.smp.web.model;

import java.util.List;

import static java.util.Arrays.asList;

public class UserModels {
    private List<UserModel> userModels = asList(createUserModel("amgolding", "top1234"));

    private UserModel createUserModel(String username, String secureToken) {
        UserModel model = new UserModel();
        model.setUsername(username);
        model.setSecureToken(secureToken);
        return model;
    }

    public UserModel getUserModel(int index) {
        return userModels.get(index);
    }
}
