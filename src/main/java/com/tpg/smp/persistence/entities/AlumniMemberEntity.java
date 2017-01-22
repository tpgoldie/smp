package com.tpg.smp.persistence.entities;

import javax.persistence.*;

@Entity(name = "alumniMember")
@Table(name = "T_ALUMNI_MEMBERS", schema = "SMP")
@AttributeOverrides({
    @AttributeOverride(name = "uniqueRegistrationNumber", column = @Column(name = "ALUMNI_MEMBER_NUMBER"))
})
public class AlumniMemberEntity extends PersonEntity {
    public void setAlumniMemberNumber(String memberNo) {
        super.setIdentificationNumber(memberNo);
    }

    public String getAlumniMemberNumber() { return getIdentificationNumber(); }
}
