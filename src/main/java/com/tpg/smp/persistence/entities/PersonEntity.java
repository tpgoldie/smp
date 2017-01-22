package com.tpg.smp.persistence.entities;

import com.tpg.smp.persistence.entities.embeddables.Name;

import javax.persistence.*;

@Entity(name = "person")
@Table(name = "T_PERSONS", schema = "SMP")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity extends BaseEntity {
    @Embedded
    private Name name;

    @OneToOne(mappedBy = "person")
    private UserEntity user;

    @Column(name = "ID_NUMBER")
    private String identificationNumber;

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
