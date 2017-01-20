package com.tpg.smp.persistence.entities;

import javax.persistence.*;

@Entity(name = "adminStaff")
@Table(name = "ADMIN_STAFF")
public class AdministrativeStaffMemberEntity extends PersonEntity {
    @Column(name = "ADMIN_STAFF_MEMBER_TYPE")
    @Enumerated(EnumType.STRING)
    private AdministrativeStaffMemberType administrativeStaffMemberType;

    @AttributeOverride(name = "uniqueNumber", column = @Column(name = "STAFF_MEMBER_NUMBER"))
    public String getStaffMemberNumber() {
        return getUniqueRegistrationNumber();
    }

    public void setStaffMemberNumber(String staffMemberId) {
        setUniqueRegistrationNumber(staffMemberId);
    }

    public AdministrativeStaffMemberType getAdministrativeStaffMemberType() {
        return administrativeStaffMemberType;
    }

    public void setAdministrativeStaffMemberType(AdministrativeStaffMemberType administrativeStaffMemberType) {
        this.administrativeStaffMemberType = administrativeStaffMemberType;
    }
}
