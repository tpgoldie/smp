package com.tpg.smp.persistence.repositories;

import com.tpg.smp.domain.ContactDetailType;
import com.tpg.smp.persistence.entities.ContactDetailsEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class StubbedContactsManagementRepository implements ContactsManagementRepository {
    private Map<ContactDetailType, ContactDetailsEntity> entities = new HashMap<>();

    public Optional<ContactDetailsEntity> get(ContactDetailType key) {
        return ofNullable(entities.get(key));
    }

    @Override
    public void save(ContactDetailsEntity entity) {
        entities.put(entity.getDetailType(), entity);
    }
}
