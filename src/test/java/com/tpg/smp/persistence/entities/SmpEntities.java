package com.tpg.smp.persistence.entities;

import com.tpg.smp.util.RandomStringGenerator;

import java.util.ArrayList;
import java.util.List;

abstract class SmpEntities<T> {
    static long COUNTER = 101;

    static final RandomStringGenerator RANDOM_STRING_GENERATOR = new RandomStringGenerator();

    List<T> entities = new ArrayList<>();

    public T getEntity(int index) { return entities.get(index); }

    void setName(String firstName, String lastName, PersonEntity entity) {
        Name name = new Name();
        name.setFirstName(firstName);
        name.setLastName(lastName);
        entity.setName(name);
    }
}
