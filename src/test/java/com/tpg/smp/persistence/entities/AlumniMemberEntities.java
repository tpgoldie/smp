package com.tpg.smp.persistence.entities;

public class AlumniMemberEntities extends PersonEntities<AlumniMemberEntity> {
    public AlumniMemberEntities() {
        entities.add(createEntity("Tony", "Golding", "tpgolding"));
    }

    private AlumniMemberEntity createEntity(String firstName, String lastName, String alumniMemberNumber) {
        AlumniMemberEntity entity = new AlumniMemberEntity();

        entity.setId(COUNTER++);
        setName(firstName, lastName, entity);
        entity.setAlumniMemberNumber(alumniMemberNumber);

        return entity;
    }

    public AlumniMemberEntity tonyGolding() { return findByUserId("tpgolding"); }
}
