package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public abstract class UserData {
    final UserEntity userEntity;
    final UserModel userModel;

    UserData(UserEntity userEntity, UserModel userModel) {
        this.userEntity = userEntity;
        this.userModel = userModel;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
