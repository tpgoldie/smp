package com.tpg.smp.services.conversion;

import com.tpg.smp.data.AcademicStaffMemberData;
import com.tpg.smp.data.AcademicStaffMembersData;
import com.tpg.smp.domain.AcademicStaffMember;
import com.tpg.smp.persistence.entities.*;
import com.tpg.smp.services.AssertAcademicStaffMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AcademicStaffMemberConverterTest {
    private AcademicStaffMemberData academicStaffMemberData = new AcademicStaffMembersData().getAcademicStaffMember(0);

    @Test
    public void convertEntityToDomainModel_entity_EntityIsConvertedToDomainModel() {
        UserEntity userEntity = academicStaffMemberData.getUserEntity();

        AcademicStaffMemberEntity entity = academicStaffMemberData.getAcademicStaffMemberEntity();

        AcademicStaffMemberConverter converter = new AcademicStaffMemberConverter(userEntity);

        AcademicStaffMember actual = converter.convert(entity);

        new AssertAcademicStaffMember(actual).matches(userEntity, entity);
    }
}
