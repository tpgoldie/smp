package com.tpg.smp.persistence.entities;

public class StudentEntities extends SmpEntities<StudentEntity> {
    public StudentEntities() {
        entities.add(createEntity("Ayana", "Golding", RANDOM_STRING_GENERATOR.generateRandomString()));
    }

    private StudentEntity createEntity(String firstName, String lastName, String uuid) {
        StudentEntity entity = new StudentEntity();

        entity.setId(COUNTER++);

        setName(firstName, lastName, entity);

        entity.setStudentNumber(uuid);

        return entity;
    }

    public StudentEntity ayanaGolding() { return getEntity(0); }
}
