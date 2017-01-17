package com.tpg.smp.services.conversion;

import com.tpg.smp.domain.Person;
import com.tpg.smp.persistence.entities.PersonEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import org.springframework.core.convert.converter.Converter;

public abstract class PersonConverter<S extends PersonEntity, T extends Person> implements Converter<S, T> {
    final UserEntity userEntity;

    PersonConverter(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
