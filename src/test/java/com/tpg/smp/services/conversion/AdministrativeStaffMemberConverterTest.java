package com.tpg.smp.services.conversion;

import com.tpg.smp.data.AdministrativeStaffMemberData;
import com.tpg.smp.data.AdministrativeStaffMembersData;
import com.tpg.smp.domain.AdministrativeStaffMember;
import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.services.AssertAdministrativeStaffMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AdministrativeStaffMemberConverterTest {
    private final AdministrativeStaffMemberData administrativeStaffMemberData = new AdministrativeStaffMembersData().getAdministrativeStaffMember(0);

    @Test
    public void convertEntityToDomainModel_entity_EntityIsConvertedToDomainModel() {
        UserEntity userEntity = administrativeStaffMemberData.getUserEntity();

        AdministrativeStaffMemberEntity entity = administrativeStaffMemberData.getAdministrativeStaffMemberEntity();

        AdministrativeStaffMemberConverter converter = new AdministrativeStaffMemberConverter(administrativeStaffMemberData.getUserEntity());

        AdministrativeStaffMember actual = converter.convert(entity);

        new AssertAdministrativeStaffMember(actual).matches(userEntity, entity);
    }
}
