package com.tpg.smp.data;

import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public class StudentData extends UserData {
    private final StudentEntity studentEntity;

    public StudentData(AuthenticatedUser authenticatedUser, UserEntity userEntity, StudentEntity studentEntity, UserModel userModel) {
        super(authenticatedUser, userEntity, userModel);

        this.studentEntity = studentEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }
}
