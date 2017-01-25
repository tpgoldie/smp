package com.tpg.smp.domain;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class AdministrativeStaffMemberType extends WithDescription {
    public static final AdministrativeStaffMemberType Secretary = new AdministrativeStaffMemberType("Secretary");
    public static final AdministrativeStaffMemberType ViceChancellor = new AdministrativeStaffMemberType("Vice Chancellor");
    public static final AdministrativeStaffMemberType Chancellor = new AdministrativeStaffMemberType("Chancellor");
    public static final AdministrativeStaffMemberType Registrar = new AdministrativeStaffMemberType("Registrar");

    private static final Set<AdministrativeStaffMemberType> TypedValues = new HashSet<>();

    public static Set<AdministrativeStaffMemberType> TypedValues() {
        if (!TypedValues.isEmpty()) { return TypedValues; }

        TypedValues.addAll(asList(Secretary, ViceChancellor, Chancellor, Registrar));

        return TypedValues;
    }

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
