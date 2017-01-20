package com.tpg.smp.services;

import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class AssertStudent extends AssertPerson {
    public AssertStudent(Student actual) {
        super(actual);
    }

    public void matches(UserEntity userEntity, StudentEntity studentEntity) {
        super.matches(userEntity, studentEntity);

        assertThat(actual, hasProperty("registrationNumber", is(studentEntity.getStudentNumber())));
    }
}
