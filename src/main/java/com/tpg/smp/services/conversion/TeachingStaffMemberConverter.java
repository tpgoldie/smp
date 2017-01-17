package com.tpg.smp.services.conversion;

import com.tpg.smp.domain.TeachingStaffMember;
import com.tpg.smp.persistence.entities.TeachingStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;

public class TeachingStaffMemberConverter extends PersonConverter<TeachingStaffMemberEntity, TeachingStaffMember> {
    public TeachingStaffMemberConverter(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public TeachingStaffMember convert(TeachingStaffMemberEntity source) {
        return new TeachingStaffMember(userEntity.getUsername(), source.getName().getFirstName(),
            source.getName().getLastName(), source.getTeachingStaffMemberType());
    }
}
