package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.TeachingStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public class TeachingStaffMemberData extends UserData {
    private final TeachingStaffMemberEntity teachingStaffMemberEntity;

    public TeachingStaffMemberData(UserEntity userEntity, TeachingStaffMemberEntity teachingStaffMemberEntity, UserModel userModel) {
        super(userEntity, userModel);

        this.teachingStaffMemberEntity = teachingStaffMemberEntity;
    }

    public TeachingStaffMemberEntity getTeachingStaffMemberEntity() {
        return teachingStaffMemberEntity;
    }
}
