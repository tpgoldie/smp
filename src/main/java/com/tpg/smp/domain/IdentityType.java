package com.tpg.smp.domain;

import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.asList;

public class IdentityType extends WithDescription {
    public static final IdentityType Passport = new IdentityType("Passport");
    public static final IdentityType EUNationalIdentityCard = new IdentityType("EU National Identity Card");
    public static final IdentityType ForeignNationalsIdentityCard = new IdentityType("Foreign Nationals Identity Card");
    public static final IdentityType BirthCertificate = new IdentityType("Birth Certificate");
    public static final IdentityType BritishDrivingLicence = new IdentityType("British Driving Licence");

    private static final Set<IdentityType> TypedValues = new TreeSet<>();

    public static Set<IdentityType> TypedValues() {
        if (!TypedValues.isEmpty()) { return TypedValues; }

        TypedValues.addAll(asList(Passport, EUNationalIdentityCard, ForeignNationalsIdentityCard, BritishDrivingLicence, BirthCertificate));
        return TypedValues;
    }

    IdentityType(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!super.equals(obj)) { return true; }

        return (obj instanceof IdentityType);
    }
}
