package com.tpg.smp.domain;

import com.tpg.smp.persistence.entities.AcademicStaffMemberType;

public class AcademicStaffMember extends Person {
    private AcademicStaffMemberType academicStaffMemberType;

    public AcademicStaffMember(Name name, String username, String registrationNumber,
                               AcademicStaffMemberType academicStaffMemberType) {
        super(name, username, registrationNumber);

        this.academicStaffMemberType = academicStaffMemberType;
    }

    public AcademicStaffMemberType getAcademicStaffMemberType() {
        return academicStaffMemberType;
    }
}
