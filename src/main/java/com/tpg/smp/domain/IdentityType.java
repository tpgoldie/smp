package com.tpg.smp.domain;

import static java.util.Arrays.asList;

public class IdentityType extends WithDescription {
    public static final IdentityType Passport = new IdentityType("Passport");
    public static final IdentityType EUNationalIdentityCard = new IdentityType("EU National Identity Card");
    public static final IdentityType ForeignNationalsIdentityCard = new IdentityType("Foreign Nationals Identity Card");
    public static final IdentityType BirthCertificate = new IdentityType("Birth Certificate");
    public static final IdentityType BritishDrivingLicence = new IdentityType("British Driving Licence");

    private static final TypedValues<IdentityType> TypedValues = new TypedValues<>(asList(Passport, EUNationalIdentityCard,
        ForeignNationalsIdentityCard, BritishDrivingLicence, BirthCertificate));

    public static TypedValues<IdentityType> TypedValues() { return TypedValues; }

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
