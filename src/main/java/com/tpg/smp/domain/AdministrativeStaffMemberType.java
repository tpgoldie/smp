package com.tpg.smp.domain;

import static java.util.Arrays.asList;

public class AdministrativeStaffMemberType extends WithDescription {
    public static final AdministrativeStaffMemberType Secretary = new AdministrativeStaffMemberType("Secretary");
    public static final AdministrativeStaffMemberType ViceChancellor = new AdministrativeStaffMemberType("Vice Chancellor");
    public static final AdministrativeStaffMemberType Chancellor = new AdministrativeStaffMemberType("Chancellor");
    public static final AdministrativeStaffMemberType Registrar = new AdministrativeStaffMemberType("Registrar");

    private static final TypedValues<AdministrativeStaffMemberType> TypedValues = new TypedValues<>(asList(Secretary, ViceChancellor, Chancellor, Registrar));

    public static TypedValues<AdministrativeStaffMemberType> TypedValues() { return TypedValues; }

    AdministrativeStaffMemberType(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!super.equals(obj)) { return true; }

        return (obj instanceof AdministrativeStaffMemberType);
    }
}
