package com.tpg.smp.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "user")
@Table(name = "USER_DETAILS", schema = "SMP")
public class UserEntity {
    private Long id;

    private String username;

    private String secureToken;

    private Long personId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecureToken() {
        return secureToken;
    }

    public void setSecureToken(String secureToken) {
        this.secureToken = secureToken;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
