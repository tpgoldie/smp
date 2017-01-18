package com.tpg.smp.services.conversion;

import com.tpg.smp.domain.AdministrativeStaffMember;
import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;

public class AdministrativeStaffMemberConverter extends PersonConverter<AdministrativeStaffMemberEntity, AdministrativeStaffMember> {
    public AdministrativeStaffMemberConverter(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public AdministrativeStaffMember convert(AdministrativeStaffMemberEntity source) {
        return new AdministrativeStaffMember(userEntity.getUsername(), source.getName().getFirstName(),
            source.getName().getLastName(), source.getAdministrativeStaffMemberType());
    }
}
