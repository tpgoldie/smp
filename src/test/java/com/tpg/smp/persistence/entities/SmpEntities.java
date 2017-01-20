package com.tpg.smp.persistence.entities;

import com.tpg.smp.persistence.entities.embeddables.Name;
import com.tpg.smp.util.RandomStringGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class SmpEntities<T> {
    static long COUNTER = 101;

    static final RandomStringGenerator RANDOM_STRING_GENERATOR = new RandomStringGenerator();

    List<T> entities = new ArrayList<>();

    void setName(String firstName, String lastName, PersonEntity entity) {
        Name name = new Name();
        name.setFirstName(firstName);
        name.setLastName(lastName);
        entity.setName(name);
    }

    abstract T findByUserId(String id);
}
