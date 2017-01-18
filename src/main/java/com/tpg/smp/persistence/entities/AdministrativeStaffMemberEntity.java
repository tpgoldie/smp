package com.tpg.smp.persistence.entities;

import javax.persistence.*;

@Entity(name = "adminStaff")
@Table(name = "ADMIN_STAFF")
public class AdministrativeStaffMemberEntity extends PersonEntity {
    @Column(name = "ADMIN_STAFF_MEMBER_TYPE")
    @Enumerated(EnumType.STRING)
    private AdministrativeStaffMemberType administrativeStaffMemberType;

    public AdministrativeStaffMemberType getAdministrativeStaffMemberType() {
        return administrativeStaffMemberType;
    }

    public void setAdministrativeStaffMemberType(AdministrativeStaffMemberType administrativeStaffMemberType) {
        this.administrativeStaffMemberType = administrativeStaffMemberType;
    }
}
