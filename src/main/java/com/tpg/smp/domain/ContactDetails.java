package com.tpg.smp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactDetails implements Serializable {
    private String telephoneNumber;

    @NotEmpty(message = "{contactnumber.missing}")
    private String mobileNumber;

    @NotEmpty(message = "{main.emailaddress.missing}")
    private String mainEmailAddress;

    public String getMainEmailAddress() {
        return mainEmailAddress;
    }

    public void setMainEmailAddress(String mainEmailAddress) {
        this.mainEmailAddress = mainEmailAddress;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof ContactDetails)) { return false; }

        ContactDetails that = (ContactDetails) obj;

        return new EqualsBuilder()
            .append(that.telephoneNumber, this.telephoneNumber)
            .append(that.mobileNumber, this.mobileNumber)
            .append(that.mainEmailAddress, this.mainEmailAddress)
            .isEquals();
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
