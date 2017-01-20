package com.tpg.smp.services;

import com.tpg.smp.domain.AcademicStaffMember;
import com.tpg.smp.persistence.entities.AcademicStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class AssertAcademicStaffMember extends AssertPerson {
    public AssertAcademicStaffMember(AcademicStaffMember actual) {
        super(actual);
    }

    public void matches(UserEntity userEntity, AcademicStaffMemberEntity academicStaffMemberEntity) {
        super.matches(userEntity, academicStaffMemberEntity);

        assertThat(actual, hasProperty("registrationNumber", is(academicStaffMemberEntity.getStaffMemberNumber())));
        assertThat(actual, hasProperty("academicStaffMemberType", is(academicStaffMemberEntity.getAcademicStaffMemberType())));
    }
}
