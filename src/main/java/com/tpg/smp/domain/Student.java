package com.tpg.smp.domain;

import com.tpg.smp.auth.AuthenticatedUser;

public class Student extends AuthenticatedUser {
    public Student(String username, String firstName, String lastName) {
        super(username, firstName, lastName);
    }
}
