package com.tpg.smp.persistence.entities.convertibles;

import com.tpg.smp.domain.TypedValues;
import com.tpg.smp.domain.WithDescription;
import org.apache.commons.lang3.reflect.Typed;

import javax.persistence.AttributeConverter;
import java.util.Set;

public abstract class ConvertiblePersistentUserDefinedType<S extends WithDescription> implements AttributeConverter<S, String> {
    S filter(String value, TypedValues<S> typedValues) {
        java.util.Optional<S> found = typedValues.stream().filter(t -> t.getDescription().equalsIgnoreCase(value)).findFirst();

        if (found.isPresent()) { return found.get(); }

        throw new RuntimeException(String.format("Failed to convert %s to correct type", value));
    }

    @Override
    public String convertToDatabaseColumn(S attribute) {
        return attribute.getDescription();
    }

    @Override
    public S convertToEntityAttribute(String dbData) {
        return filter(dbData, typedValues());
    }

    abstract TypedValues<S> typedValues();
}
