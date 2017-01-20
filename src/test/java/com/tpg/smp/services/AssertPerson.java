package com.tpg.smp.services;

import com.tpg.smp.domain.Person;
import com.tpg.smp.persistence.entities.PersonEntity;
import com.tpg.smp.persistence.entities.UserEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public abstract class AssertPerson<T extends Person> {
    T actual;

    AssertPerson(T actual) {
        this.actual = actual;
    }


    void matches(UserEntity userEntity, PersonEntity personEntity) {
        assertThat(actual, hasProperty("username", is(userEntity.getUsername())));
        assertThat(actual, hasProperty("firstName", is(personEntity.getName().getFirstName())));
        assertThat(actual, hasProperty("lastName", is(personEntity.getName().getLastName())));
        assertThat(actual, hasProperty("registrationNumber", is(personEntity.getUniqueRegistrationNumber())));
    }
}
