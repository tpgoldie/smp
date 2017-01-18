package com.tpg.smp.persistence.entities;

import static com.tpg.smp.persistence.entities.AdministrativeStaffMemberType.Registrar;

public class AdministrativeStaffMemberEntities extends SmpEntities<AdministrativeStaffMemberEntity> {
    public AdministrativeStaffMemberEntities() {
        entities.add(createEntity("Roger", "Johnson", Registrar));
    }

    private AdministrativeStaffMemberEntity createEntity(String firstName, String lastName,
                                                         AdministrativeStaffMemberType administrativeStaffMemberType) {
        AdministrativeStaffMemberEntity entity = new AdministrativeStaffMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setAdministrativeStaffMemberType(administrativeStaffMemberType);

        return entity;
    }

    public AdministrativeStaffMemberEntity rogerJohnson() { return getEntity(0); }
}
