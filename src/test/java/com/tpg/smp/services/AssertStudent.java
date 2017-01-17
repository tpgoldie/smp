package com.tpg.smp.services;

import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class AssertStudent {
    private final Student actual;

    public AssertStudent(Student actual) {
        this.actual = actual;
    }

    public void matches(UserEntity userEntity, StudentEntity studentEntity) {
        assertThat(actual, hasProperty("username", is(userEntity.getUsername())));
        assertThat(actual, hasProperty("firstName", is(studentEntity.getName().getFirstName())));
        assertThat(actual, hasProperty("lastName", is(studentEntity.getName().getLastName())));
        assertThat(actual, hasProperty("studentNumber", is(studentEntity.getStudentNumber())));
    }
}
