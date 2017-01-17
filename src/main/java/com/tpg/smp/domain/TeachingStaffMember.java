package com.tpg.smp.domain;

import com.tpg.smp.persistence.entities.TeachingStaffMemberType;

public class TeachingStaffMember extends Person {
    private TeachingStaffMemberType teachingStaffMemberType;

    public TeachingStaffMember(String username, String firstName, String lastName, TeachingStaffMemberType teachingStaffMemberType) {
        super(username, firstName, lastName);

        this.teachingStaffMemberType =teachingStaffMemberType;
    }

    public TeachingStaffMemberType getTeachingStaffMemberType() {
        return teachingStaffMemberType;
    }
}
