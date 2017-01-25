package com.tpg.smp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;

public class IdentityDetails implements Serializable {
    private String identityNumberType;

    private String identityNumber;

    public String getIdentityNumberType() {
        return identityNumberType;
    }

    public void setIdentityType(String identityNumberType) {
        this.identityNumberType = identityNumberType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof IdentityDetails)) { return false; }

        IdentityDetails that = (IdentityDetails) obj;

        return new EqualsBuilder()
            .append(that.identityNumberType, this.identityNumberType)
            .append(that.identityNumber, this.identityNumber)
            .isEquals();
    }
}
