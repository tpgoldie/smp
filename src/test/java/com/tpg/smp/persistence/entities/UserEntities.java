package com.tpg.smp.persistence.entities;

public class UserEntities extends SmpEntities<UserEntity> {
    public UserEntities() {
        entities.add(createEntity("amgolding", "top1234"));
    }

    private UserEntity createEntity(String username, String secureToken) {
        UserEntity entity = new UserEntity();

        entity.setId(COUNTER++);
        entity.setUsername(username);
        entity.setSecureToken(secureToken);

        return entity;
    }
}
