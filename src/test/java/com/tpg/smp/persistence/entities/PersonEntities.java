package com.tpg.smp.persistence.entities;

import java.util.Optional;

public abstract class PersonEntities<T extends PersonEntity> extends SmpEntities<T> {
    T findByUserId(String id) {
        Optional<T> found = entities.stream().filter(e -> e.getIdentificationNumber().equalsIgnoreCase(id)).findAny();

        if (found.isPresent()) { return found.get(); }

        throw new RuntimeException(String.format("Entity %s not found", id));
    }
}
