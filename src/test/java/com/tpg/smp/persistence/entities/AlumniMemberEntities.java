package com.tpg.smp.persistence.entities;

import com.tpg.smp.domain.GenderType;

public class AlumniMemberEntities extends PersonEntities<AlumniMemberEntity> {
    public AlumniMemberEntities() {
        entities.add(createEntity("Tony", "Golding", GenderType.Male, "tpgolding"));
    }

    private AlumniMemberEntity createEntity(String firstName, String lastName, GenderType gender, String alumniMemberNumber) {
        AlumniMemberEntity entity = new AlumniMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setGender(gender);
        entity.setAlumniMemberNumber(alumniMemberNumber);

        return entity;
    }

    public AlumniMemberEntity tonyGolding() { return findByUserId("tpgolding"); }
}
