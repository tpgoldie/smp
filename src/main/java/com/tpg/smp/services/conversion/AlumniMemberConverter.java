package com.tpg.smp.services.conversion;

import com.tpg.smp.domain.AlumniMember;
import com.tpg.smp.domain.Name;
import com.tpg.smp.persistence.entities.AlumniMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;

public class AlumniMemberConverter extends PersonConverter<AlumniMemberEntity, AlumniMember> {
    public AlumniMemberConverter(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public AlumniMember convert(AlumniMemberEntity source) {
        return new AlumniMember(new Name(source.getName().getFirstName(), source.getName().getLastName()),
          userEntity.getUsername(), source.getAlumniMemberNumber());
    }
}
