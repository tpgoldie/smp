package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public class StudentData extends UserData {
    private final StudentEntity studentEntity;

    public StudentData(UserEntity userEntity, StudentEntity studentEntity, UserModel userModel) {
        super(userEntity, userModel);

        this.studentEntity = studentEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
