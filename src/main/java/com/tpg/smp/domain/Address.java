package com.tpg.smp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.List;

public class Address {
    private List<String> lines = new ArrayList<>();

    private String region;

    private String country;

    private String postCode;

    public String getLine(int index) { return lines.get(index); }

    public void setLine(int index, String value) { lines.set(index, value); }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Address)) { return false; }

        Address that = (Address) obj;

        return new EqualsBuilder()
            .append(that.lines, this.lines)
            .append(that.region, this.region)
            .append(that.postCode, this.postCode)
            .append(that.country, this.country)
            .isEquals();
    }
}
