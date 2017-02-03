package com.tpg.smp.domain;

import static java.util.Arrays.asList;

public class GenderType extends WithDescription {
    public static final GenderType Male = new GenderType("Male", "M");

    public static final GenderType Female = new GenderType("Female", "F");

    private static final TypedValues<GenderType> TypedValues = new TypedValues<>(asList(Male, Female));

    public static TypedValues<GenderType> TypedValues() { return TypedValues; }

    private final String name;
    private final String symbol;

    GenderType(String name, String symbol) {
        super(name);

        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
