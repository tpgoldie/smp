package com.tpg.smp.data;

import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public abstract class UserData {
    final AuthenticatedUser authenticatedUser;
    final UserEntity userEntity;
    final UserModel userModel;

    UserData(AuthenticatedUser authenticatedUser, UserEntity userEntity, UserModel userModel) {
        this.authenticatedUser = authenticatedUser;
        this.userEntity = userEntity;
        this.userModel = userModel;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public UserModel getUserModel() { return userModel; }

    public AuthenticatedUser getAuthenticatedUser() { return authenticatedUser; }
}
