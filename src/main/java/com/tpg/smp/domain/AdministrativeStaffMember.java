package com.tpg.smp.domain;

import com.tpg.smp.persistence.entities.AdministrativeStaffMemberType;

public class AdministrativeStaffMember extends Person {
    private AdministrativeStaffMemberType administrativeStaffMemberType;

    public AdministrativeStaffMember(String username, String firstName, String lastName, AdministrativeStaffMemberType administrativeStaffMemberType) {
        super(username, firstName, lastName);

        this.administrativeStaffMemberType = administrativeStaffMemberType;
    }

    public AdministrativeStaffMemberType getAdministrativeStaffMemberType() {
        return administrativeStaffMemberType;
    }
}
