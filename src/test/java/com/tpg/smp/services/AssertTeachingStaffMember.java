package com.tpg.smp.services;

import com.tpg.smp.domain.TeachingStaffMember;
import com.tpg.smp.persistence.entities.TeachingStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class AssertTeachingStaffMember extends AssertPerson {
    public AssertTeachingStaffMember(TeachingStaffMember actual) {
        super(actual);
    }

    public void matches(UserEntity userEntity, TeachingStaffMemberEntity teachingStaffMemberEntity) {
        super.matches(userEntity, teachingStaffMemberEntity);

        assertThat(actual, hasProperty("teachingStaffMemberType", is(teachingStaffMemberEntity.getTeachingStaffMemberType())));
    }
}
