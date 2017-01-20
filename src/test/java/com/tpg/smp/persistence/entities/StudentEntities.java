package com.tpg.smp.persistence.entities;

public class StudentEntities extends PersonEntities<StudentEntity> {
    public StudentEntities() {
        entities.add(createEntity("Michael", "Danque", "midanque"));
    }

    private StudentEntity createEntity(String firstName, String lastName, String studentName) {
        StudentEntity entity = new StudentEntity();

        entity.setId(COUNTER++);

        setName(firstName, lastName, entity);

        entity.setStudentNumber(studentName);

        return entity;
    }

    public StudentEntity michaelDanque() { return findByUserId("midanque"); }
}
