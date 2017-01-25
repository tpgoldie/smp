package com.tpg.smp.data;

import com.tpg.smp.auth.AuthenticatedUser;
import com.tpg.smp.domain.Person;
import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.web.model.UserModel;

public class AdministrativeStaffMemberData extends UserData {
    private final AdministrativeStaffMemberEntity administrativeStaffMemberEntity;

    public AdministrativeStaffMemberData(AuthenticatedUser authenticatedUser, UserEntity userEntity,
                                         AdministrativeStaffMemberEntity administrativeStaffMemberEntity, UserModel userModel,
                                         Person domainModel) {
        super(authenticatedUser, userEntity, userModel, domainModel);

        this.administrativeStaffMemberEntity = administrativeStaffMemberEntity;
    }

    public AdministrativeStaffMemberEntity getAdministrativeStaffMemberEntity() {
        return administrativeStaffMemberEntity;
    }
}
