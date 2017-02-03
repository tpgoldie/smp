package com.tpg.smp.domain;

import static java.util.Arrays.asList;

public class ContactDetailType extends WithDescription {
    public static final ContactDetailType EmailAddress = new ContactDetailType("EmailAddress", "Email address");
    public static final ContactDetailType TelephoneNumber = new ContactDetailType("TelephoneNumber", "Telephone number");
    public static final ContactDetailType MobileNumber = new ContactDetailType("MobileNumber", "Mobile number");

    private static final TypedValues<ContactDetailType> TypedValues = new TypedValues<>(asList(EmailAddress, TelephoneNumber, MobileNumber));

    public static TypedValues<ContactDetailType> TypedValues() { return TypedValues; }

    private final String name;

    ContactDetailType(String name, String description) {
        super(description);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
