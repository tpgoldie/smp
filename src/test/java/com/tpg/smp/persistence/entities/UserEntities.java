package com.tpg.smp.persistence.entities;

public class UserEntities extends SmpEntities<UserEntity> {
    public UserEntities() {
        entities.add(createEntity("amgolding", "top1234"));
        entities.add(createEntity("viwestwood", "t4p1534"));
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
}
