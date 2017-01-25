package com.tpg.smp.persistence.entities.convertibles;

import com.tpg.smp.domain.AdministrativeStaffMemberType;

import javax.persistence.Converter;
import java.util.Set;

@Converter(autoApply = true)
public class AdministrativeStaffMemberTypeConvertible extends ConvertiblePersistentUserDefinedType<AdministrativeStaffMemberType> {
    @Override
    Set<AdministrativeStaffMemberType> typedValues() {
        return AdministrativeStaffMemberType.TypedValues();
    }
}
