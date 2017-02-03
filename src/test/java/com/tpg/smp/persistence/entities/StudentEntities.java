package com.tpg.smp.persistence.entities;

import com.tpg.smp.domain.GenderType;

public class StudentEntities extends PersonEntities<StudentEntity> {
    public StudentEntities() {
        entities.add(createEntity("Michael", "Danque", GenderType.Male, "midanque"));
    }

    private StudentEntity createEntity(String firstName, String lastName, GenderType gender, String studentName) {
        StudentEntity entity = new StudentEntity();

        entity.setId(COUNTER++);

        setName(firstName, lastName, entity);

        entity.setGender(gender);
        entity.setStudentNumber(studentName);

        return entity;
    }

    public StudentEntity michaelDanque() { return findByUserId("midanque"); }
}
