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

    private UserModel findByUserId(String id) {
        java.util.Optional<UserModel> found = userModels.stream().filter(um -> um.getUsername().equalsIgnoreCase(id)).findAny();

        if (!found.isPresent()) { throw new RuntimeException(String.format("User model %s not found", id)); }

        return found.get();
    }

    public UserModel michaelDanque() { return findByUserId("midanque"); }

    public UserModel vienneWestwood() { return findByUserId("viwestwood"); }

    public UserModel rogerJohnson() { return findByUserId("rojohnson"); }

    public UserModel tonyGolding() { return findByUserId("tpgolding"); }

}
