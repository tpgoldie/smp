package com.tpg.smp.services.conversion;

import com.tpg.smp.domain.AcademicStaffMember;
import com.tpg.smp.domain.Name;
import com.tpg.smp.persistence.entities.AcademicStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;

public class AcademicStaffMemberConverter extends PersonConverter<AcademicStaffMemberEntity, AcademicStaffMember> {
    public AcademicStaffMemberConverter(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public AcademicStaffMember convert(AcademicStaffMemberEntity source) {
        return new AcademicStaffMember(new Name(source.getName().getFirstName(), source.getName().getLastName()),
            userEntity.getUsername(), source.getStaffMemberNumber(), source.getAcademicStaffMemberType());
    }
}
