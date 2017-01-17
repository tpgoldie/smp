package com.tpg.smp.persistence.entities;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TeachingStaffMemberEntity extends PersonEntity {
    @Column(name = "TEACHING_STAFF_MEMBER_TYPE")
    @Enumerated(EnumType.STRING)
    private TeachingStaffMemberType teachingStaffMemberType;

    public TeachingStaffMemberType getTeachingStaffMemberType() {
        return teachingStaffMemberType;
    }

    public void setTeachingStaffMemberType(TeachingStaffMemberType teachingStaffMemberType) {
        this.teachingStaffMemberType = teachingStaffMemberType;
    }
}
