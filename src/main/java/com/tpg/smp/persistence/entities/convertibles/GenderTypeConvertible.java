package com.tpg.smp.persistence.entities.convertibles;

import com.tpg.smp.domain.GenderType;
import com.tpg.smp.domain.TypedValues;

import java.util.Set;

public class GenderTypeConvertible extends ConvertiblePersistentUserDefinedType<GenderType> {
    @Override
    TypedValues<GenderType> typedValues() {
        return GenderType.TypedValues();
    }
}
