package com.tpg.smp.persistence.entities.convertibles;

import com.tpg.smp.domain.AdministrativeStaffMemberType;
import com.tpg.smp.domain.TypedValues;

import javax.persistence.Converter;
import java.util.Set;

@Converter(autoApply = true)
public class AdministrativeStaffMemberTypeConvertible extends ConvertiblePersistentUserDefinedType<AdministrativeStaffMemberType> {
    @Override
    TypedValues<AdministrativeStaffMemberType> typedValues() {
        return AdministrativeStaffMemberType.TypedValues();
    }
}
