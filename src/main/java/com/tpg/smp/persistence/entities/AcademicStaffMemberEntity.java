package com.tpg.smp.persistence.entities;

import javax.persistence.*;

@Entity(name = "academicStaffMember")
@Table(name = "TEACHING_STAFF", schema = "SMP")
public class AcademicStaffMemberEntity extends PersonEntity {
    @Column(name = "TEACHING_STAFF_MEMBER_TYPE")
    @Enumerated(EnumType.STRING)
    private AcademicStaffMemberType academicStaffMemberType;

    public AcademicStaffMemberType getAcademicStaffMemberType() {
        return academicStaffMemberType;
    }

    public void setAcademicStaffMemberType(AcademicStaffMemberType academicStaffMemberType) {
        this.academicStaffMemberType = academicStaffMemberType;
    }
}
