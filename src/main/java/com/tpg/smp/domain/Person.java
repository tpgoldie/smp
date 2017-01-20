package com.tpg.smp.domain;


import com.tpg.smp.auth.AuthenticatedUser;

public abstract class Person extends AuthenticatedUser {
    private String registrationNumber;

    public Person(Name name, String username, String registrationNumber) {
        super(name, username);

        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
