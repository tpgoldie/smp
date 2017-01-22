package com.tpg.smp.persistence.entities;

import com.tpg.smp.persistence.entities.convertibles.AdministrativeStaffMemberTypeConvertible;
import com.tpg.smp.persistence.entities.udts.AdministrativeStaffMemberType;

import javax.persistence.*;

@Entity(name = "adminStaff")
@Table(name = "T_ADMIN_STAFF", schema = "SMP")
public class AdministrativeStaffMemberEntity extends PersonEntity {
    @Column(name = "ADMIN_STAFF_MEMBER_TYPE")
    @Convert(converter = AdministrativeStaffMemberTypeConvertible.class)
    private AdministrativeStaffMemberType administrativeStaffMemberType;

    public AdministrativeStaffMemberType getAdministrativeStaffMemberType() {
        return administrativeStaffMemberType;
    }

    public void setAdministrativeStaffMemberType(AdministrativeStaffMemberType administrativeStaffMemberType) {
        this.administrativeStaffMemberType = administrativeStaffMemberType;
    }
}
