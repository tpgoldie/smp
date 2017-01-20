package com.tpg.smp.persistence.entities;

import com.tpg.smp.data.PasswordGenerator;

import java.util.List;
import java.util.Optional;

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

    UserEntity findByUserId(String id) {
        Optional<UserEntity> found = entities.stream().filter(e -> e.getUsername().equalsIgnoreCase(id)).findAny();

        if (found.isPresent()) { return found.get(); }

        throw new RuntimeException(String.format("Entity %s not found", id));
    }

    public UserEntity michaelDanque() { return findByUserId("midanque"); }

    public UserEntity vienneWestwood() { return findByUserId("viwestwood"); }

    public UserEntity rogerJohnson() { return findByUserId("rojohnson"); }

    public UserEntity tonyGolding() { return findByUserId("tpgolding"); }
}
