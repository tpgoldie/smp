package com.tpg.smp.persistence.entities;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "T_USERS_DETAILS", schema = "SMP")
public class UserEntity extends BaseEntity {
    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "SECURE_TOKEN")
    private String secureToken;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;

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

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
