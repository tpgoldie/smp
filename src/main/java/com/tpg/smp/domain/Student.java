package com.tpg.smp.domain;

import com.tpg.smp.auth.AuthenticatedUser;

public class Student extends AuthenticatedUser {
    private String studentNumber;

    public Student(String username, String firstName, String lastName, String studentNumber) {
        super(username, firstName, lastName);

        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
}
