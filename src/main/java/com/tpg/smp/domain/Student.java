package com.tpg.smp.domain;

public class Student extends Person {
    private String studentNumber;

    public Student(String username, String firstName, String lastName, String studentNumber) {
        super(username, firstName, lastName);

        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
}
