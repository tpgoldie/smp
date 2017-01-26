package com.tpg.smp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactDetails implements Serializable {
    @NotEmpty(message = "{contactnumber.missing}")
    private List<String> contactNumbers = new ArrayList<>();

    @NotEmpty(message = "{main.emailaddress.missing}")
    private String mainEmailAddress;

    public String getContactNumber(int index) { return contactNumbers.get(index); }

    public void setContactNumber(int index, String number) { contactNumbers.set(index, number); }

    public void addContactNumber(String number) { contactNumbers.add(number); }

    public String getMainEmailAddress() {
        return mainEmailAddress;
    }

    public void setMainEmailAddress(String mainEmailAddress) {
        this.mainEmailAddress = mainEmailAddress;
    }

    public List<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(List<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof ContactDetails)) { return false; }

        ContactDetails that = (ContactDetails) obj;

        return new EqualsBuilder()
            .append(that.contactNumbers, this.contactNumbers)
            .append(that.mainEmailAddress, this.mainEmailAddress)
            .isEquals();
    }
}
