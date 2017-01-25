package com.tpg.smp.data;

import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.domain.Person;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public abstract class UserData {
    final AuthenticatedUser authenticatedUser;
    final UserEntity userEntity;
    final UserModel userModel;
    Person domainModel;

    UserData(AuthenticatedUser authenticatedUser, UserEntity userEntity, UserModel userModel, Person domainModel) {
        this.authenticatedUser = authenticatedUser;
        this.userEntity = userEntity;
        this.userModel = userModel;
        this.domainModel = domainModel;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public UserModel getUserModel() { return userModel; }

    public AuthenticatedUser getAuthenticatedUser() { return authenticatedUser; }

    public Person getDomainModel() { return domainModel; }
}
