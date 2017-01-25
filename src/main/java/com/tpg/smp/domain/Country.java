package com.tpg.smp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Country implements Comparable<Country> {
    public static final Country France = new Country("France", "FR");
    public static final Country Germany = new Country("Germany", "GER");
    public static final Country UnitedKingdom = new Country("United Kingdom", "UK");
    public static final Country UnitedStates = new Country("United States Of America", "USA");

    private final String name;

    private final String symbol;

    Country(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Country)) { return false; }

        Country that = (Country) obj;

        return new EqualsBuilder()
            .append(that.name, this.name)
            .append(that.symbol, this.symbol)
                .isEquals();
    }

    @Override
    public int compareTo(Country that) {
        return this.getName().compareTo(that.getName());
    }
}
