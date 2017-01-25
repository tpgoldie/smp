package com.tpg.smp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;

public class Name implements Serializable {
    private String firstName;
    private String lastName;

    public Name() {}

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Name)) { return false; }

        Name that = (Name) obj;

        return new EqualsBuilder()
            .append(that.getFirstName(), this.getFirstName())
            .append(that.getLastName(), this.getLastName())
            .isEquals();
    }

    @Override
    public String toString() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
