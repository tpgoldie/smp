package com.tpg.smp.domain;


import com.tpg.smp.auth.AuthenticatedUser;

public abstract class Person extends AuthenticatedUser {
    public Person(String username, String firstName, String lastName) {
        super(username, firstName, lastName);
    }
}
