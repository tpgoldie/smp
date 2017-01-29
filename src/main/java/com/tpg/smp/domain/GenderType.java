package com.tpg.smp.domain;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class GenderType {
    public static final GenderType Male = new GenderType("M", "Male");
    public static final GenderType Female = new GenderType("F", "Female");

    private static Set<GenderType> TypedValues = new HashSet<>();

    public static Set<GenderType> TypedValues() {
        if (!TypedValues.isEmpty()) { return TypedValues; }

        TypedValues.addAll(asList(Male, Female));

        return TypedValues;
    }

    private String name;
    private String symbol;

    GenderType(String name, String symbol) {
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
