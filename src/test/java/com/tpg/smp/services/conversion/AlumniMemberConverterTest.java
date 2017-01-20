package com.tpg.smp.services.conversion;

import com.tpg.smp.data.AlumniMemberData;
import com.tpg.smp.data.AlumniMembersData;
import com.tpg.smp.domain.AlumniMember;
import com.tpg.smp.persistence.entities.AlumniMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.services.AssertAlumniMember;
import org.junit.Test;

public class AlumniMemberConverterTest {
    private AlumniMemberData alumniMemberData = new AlumniMembersData().getAlumniMember(0);

    @Test
    public void convertEntityToDomainModel_entity_EntityIsConvertedToDomainModel() {
        UserEntity userEntity = alumniMemberData.getUserEntity();

        AlumniMemberEntity alumniMemberEntity = alumniMemberData.getAlumniMemberEntity();

        AlumniMemberConverter converter = new AlumniMemberConverter(userEntity);

        AlumniMember actual = converter.convert(alumniMemberEntity);

        new AssertAlumniMember(actual).matches(userEntity, alumniMemberEntity);
    }
}
