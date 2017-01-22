package com.tpg.smp.persistence.entities;

import javax.persistence.*;

@Entity(name = "student")
@Table(name = "T_STUDENTS", schema = "SMP")
public class StudentEntity extends PersonEntity {
    @AttributeOverride(name = "uniqueRegistrationNumber", column = @Column(name = "STUDENT_NUMBER"))
    public String getStudentNumber() {
        return getIdentificationNumber();
    }

    public void setStudentNumber(String studentNumber) {
        setIdentificationNumber(studentNumber);
    }
}
