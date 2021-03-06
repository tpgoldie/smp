package com.tpg.smp.persistence.entities.convertibles;

import com.tpg.smp.domain.AcademicStaffMemberType;
import com.tpg.smp.domain.TypedValues;

import javax.persistence.Converter;
import java.util.Set;


@Converter(autoApply = true)
public class AcademicStaffMemberTypeConvertible extends ConvertiblePersistentUserDefinedType<AcademicStaffMemberType> {
    @Override
    TypedValues<AcademicStaffMemberType> typedValues() {
        return AcademicStaffMemberType.TypedValues();
    }
}
