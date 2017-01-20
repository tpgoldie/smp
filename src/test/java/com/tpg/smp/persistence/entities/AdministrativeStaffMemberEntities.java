package com.tpg.smp.persistence.entities;

import static com.tpg.smp.persistence.entities.AdministrativeStaffMemberType.Registrar;

public class AdministrativeStaffMemberEntities extends PersonEntities<AdministrativeStaffMemberEntity> {
    public AdministrativeStaffMemberEntities() {
        entities.add(createEntity("Roger", "Johnson", "rojohnson", Registrar));
    }

    private AdministrativeStaffMemberEntity createEntity(String firstName, String lastName, String staffMemberNumber,
                                                         AdministrativeStaffMemberType administrativeStaffMemberType) {
        AdministrativeStaffMemberEntity entity = new AdministrativeStaffMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setStaffMemberNumber(staffMemberNumber);
        entity.setAdministrativeStaffMemberType(administrativeStaffMemberType);

        return entity;
    }

    public AdministrativeStaffMemberEntity rogerJohnson() { return findByUserId("rojohnson"); }
}
