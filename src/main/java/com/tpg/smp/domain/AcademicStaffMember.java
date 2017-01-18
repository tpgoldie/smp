package com.tpg.smp.domain;

import com.tpg.smp.persistence.entities.AcademicStaffMemberType;

public class AcademicStaffMember extends Person {
    private AcademicStaffMemberType academicStaffMemberType;

    public AcademicStaffMember(String username, String firstName, String lastName, AcademicStaffMemberType academicStaffMemberType) {
        super(username, firstName, lastName);

        this.academicStaffMemberType = academicStaffMemberType;
    }

    public AcademicStaffMemberType getAcademicStaffMemberType() {
        return academicStaffMemberType;
    }
}
