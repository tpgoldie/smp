package com.tpg.smp.services.conversion;

import com.tpg.smp.domain.Student;
import com.tpg.smp.domain.TeachingStaffMember;
import com.tpg.smp.persistence.entities.*;
import com.tpg.smp.services.AssertStudent;
import com.tpg.smp.services.AssertTeachingStaffMember;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TeachingStaffMemberConverterTest {
    private TeachingStaffMemberConverter converter;

    private final UserEntities userEntities = new UserEntities();

    private final TeachingStaffMemberEntities teachingStaffMemberEntities = new TeachingStaffMemberEntities();

    @Before
    public void setUp() {
        UserEntity userEntity = userEntities.getEntity(0);
        converter = new TeachingStaffMemberConverter(userEntity);
    }

    @Test
    public void convertEntityToDomainModel_entity_EntityIsConvertedToDomainModel() {
        UserEntity userEntity = userEntities.getEntity(0);

        TeachingStaffMemberEntity entity = teachingStaffMemberEntities.getEntity(0);

        TeachingStaffMember actual = converter.convert(entity);

        new AssertTeachingStaffMember(actual).matches(userEntity, entity);
    }
}
