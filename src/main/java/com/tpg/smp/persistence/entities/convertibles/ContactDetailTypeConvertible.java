package com.tpg.smp.persistence.entities.convertibles;

import com.tpg.smp.domain.ContactDetailType;
import com.tpg.smp.domain.TypedValues;

public class ContactDetailTypeConvertible extends ConvertiblePersistentUserDefinedType<ContactDetailType> {
    @Override
    TypedValues<ContactDetailType> typedValues() {
        return ContactDetailType.TypedValues();
    }
}
