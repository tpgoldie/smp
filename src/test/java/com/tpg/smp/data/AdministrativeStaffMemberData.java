package com.tpg.smp.data;

import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public class AdministrativeStaffMemberData extends UserData {
    private final AdministrativeStaffMemberEntity administrativeStaffMemberEntity;

    public AdministrativeStaffMemberData(AuthenticatedUser authenticatedUser, UserEntity userEntity, AdministrativeStaffMemberEntity administrativeStaffMemberEntity, UserModel userModel) {
        super(authenticatedUser, userEntity, userModel);

        this.administrativeStaffMemberEntity = administrativeStaffMemberEntity;
    }

    public AdministrativeStaffMemberEntity getAdministrativeStaffMemberEntity() {
        return administrativeStaffMemberEntity;
    }
}
