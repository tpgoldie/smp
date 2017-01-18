package com.tpg.smp.data;

import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.persistence.entities.AcademicStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public class AcademicStaffMemberData extends UserData {
    private final AcademicStaffMemberEntity academicStaffMemberEntity;

    public AcademicStaffMemberData(AuthenticatedUser authenticatedUser, UserEntity userEntity,
                                   AcademicStaffMemberEntity academicStaffMemberEntity, UserModel userModel) {
        super(authenticatedUser, userEntity, userModel);

        this.academicStaffMemberEntity = academicStaffMemberEntity;
    }

    public AcademicStaffMemberEntity getAcademicStaffMemberEntity() {
        return academicStaffMemberEntity;
    }
}
