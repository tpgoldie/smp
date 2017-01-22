package com.tpg.smp.services;

import com.tpg.smp.domain.AdministrativeStaffMember;
import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class AssertAdministrativeStaffMember extends AssertPerson {
    public AssertAdministrativeStaffMember(AdministrativeStaffMember actual) {
        super(actual);
    }

    public void matches(UserEntity userEntity, AdministrativeStaffMemberEntity administrativeStaffMemberEntity) {
        super.matches(userEntity, administrativeStaffMemberEntity);

        assertThat(actual, hasProperty("registrationNumber", is(administrativeStaffMemberEntity.getIdentificationNumber())));
        assertThat(actual, hasProperty("administrativeStaffMemberType", is(administrativeStaffMemberEntity.getAdministrativeStaffMemberType())));
    }
}
