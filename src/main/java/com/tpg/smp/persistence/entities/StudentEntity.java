package com.tpg.smp.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "student")
@Table(name = "STUDENT", schema = "SMP")
public class StudentEntity extends PersonEntity {
    private String studentNumber;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
