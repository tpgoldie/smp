package com.tpg.smp.persistence.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "student")
@Table(name = "STUDENT", schema = "SMP")
public class StudentEntity extends PersonEntity {
    @AttributeOverride(name = "uniqueId", column = @Column(name = "STUDENT_NUMBER"))
    public String getStudentNumber() {
        return getUniqueRegistrationNumber();
    }

    public void setStudentNumber(String studentNumber) {
        setUniqueRegistrationNumber(studentNumber);
    }
}
