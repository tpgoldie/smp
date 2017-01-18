package com.tpg.smp.persistence.entities;

import com.tpg.smp.data.PasswordGenerator;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserEntities extends SmpEntities<UserEntity> {
    public UserEntities(List<String> userIds, PasswordGenerator passwordGenerator) {
        userIds.stream().map(id -> entities.add(createEntity(id, passwordGenerator.getPasswordFor(id))))
            .collect(toList());
    }

    private UserEntity createEntity(String username, String secureToken) {
        UserEntity entity = new UserEntity();

        entity.setId(COUNTER++);
        entity.setUsername(username);
        entity.setSecureToken(secureToken);

        return entity;
    }

    public UserEntity ayanaGolding() { return getEntity(0); }

    public UserEntity vienneWestwood() { return getEntity(1); }

    public UserEntity rogerJohnson() { return getEntity(2); }
}
