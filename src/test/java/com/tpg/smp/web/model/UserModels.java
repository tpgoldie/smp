package com.tpg.smp.web.model;

import com.tpg.smp.data.PasswordGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class UserModels {
    private List<UserModel> userModels;

    public UserModels(List<String> userIds, PasswordGenerator passwordGenerator) {
        userModels = userIds.stream().map(id -> createUserModel(id, passwordGenerator.getPasswordFor(id))).collect(Collectors.toList());
    }

    private UserModel createUserModel(String username, String secureToken) {
        UserModel model = new UserModel();
        model.setUsername(username);
        model.setSecureToken(secureToken);

        return model;
    }

    public UserModel getUserModel(int index) {
        return userModels.get(index);
    }

    public UserModel ayanaGolding() { return getUserModel(0); }

    public UserModel vienneWestwood() { return getUserModel(1); }

    public UserModel rogerJohnson() { return getUserModel(2); }

}
