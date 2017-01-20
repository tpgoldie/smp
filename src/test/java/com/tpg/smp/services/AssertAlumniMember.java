package com.tpg.smp.services;

import com.tpg.smp.domain.AlumniMember;
import com.tpg.smp.persistence.entities.AlumniMemberEntity;
import com.tpg.smp.persistence.entities.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class AssertAlumniMember extends AssertPerson {
    public AssertAlumniMember(AlumniMember actual) {
        super(actual);
    }

    public void matches(UserEntity userEntity, AlumniMemberEntity alumniMemberEntity) {
        super.matches(userEntity, alumniMemberEntity);

        assertThat(actual, hasProperty("registrationNumber", is(alumniMemberEntity.getAlumniMemberNumber())));
    }
}
