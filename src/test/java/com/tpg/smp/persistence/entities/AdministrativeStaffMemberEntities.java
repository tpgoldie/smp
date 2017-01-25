package com.tpg.smp.persistence.entities;

import com.tpg.smp.domain.AdministrativeStaffMemberType;

import static com.tpg.smp.domain.AdministrativeStaffMemberType.Registrar;

public class AdministrativeStaffMemberEntities extends PersonEntities<AdministrativeStaffMemberEntity> {
    public AdministrativeStaffMemberEntities() {
        entities.add(createEntity("Roger", "Johnson", "rojohnson", Registrar));
    }

    private AdministrativeStaffMemberEntity createEntity(String firstName, String lastName, String staffMemberNumber,
                                                         AdministrativeStaffMemberType administrativeStaffMemberType) {
        AdministrativeStaffMemberEntity entity = new AdministrativeStaffMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setIdentificationNumber(staffMemberNumber);
        entity.setAdministrativeStaffMemberType(administrativeStaffMemberType);

        return entity;
    }

    public AdministrativeStaffMemberEntity rogerJohnson() { return findByUserId("rojohnson"); }
}
