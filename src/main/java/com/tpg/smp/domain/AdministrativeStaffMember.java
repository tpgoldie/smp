package com.tpg.smp.domain;

public class AdministrativeStaffMember extends Person {
    private AdministrativeStaffMemberType administrativeStaffMemberType;

    public AdministrativeStaffMember(Name name, String username, String registrationNumber,
                                     AdministrativeStaffMemberType administrativeStaffMemberType) {
        super(name, username, registrationNumber);

        this.administrativeStaffMemberType = administrativeStaffMemberType;
    }

    public AdministrativeStaffMemberType getAdministrativeStaffMemberType() {
        return administrativeStaffMemberType;
    }
}
