package com.tpg.smp.persistence.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "alumni")
@Table(name = "ALUMNI_MEMBERS", schema = "SMP")
public class AlumniMemberEntity extends PersonEntity {
    @AttributeOverride(name = "uniqueId", column = @Column(name = "ALUMNI_MEMBER_NUMBER"))
    public void setAlumniMemberNumber(String memberNo) {
        super.setUniqueRegistrationNumber(memberNo);
    }

    public String getAlumniMemberNumber() { return getUniqueRegistrationNumber(); }
}
