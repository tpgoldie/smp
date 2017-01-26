package com.tpg.smp.web.controllers.forms;

import com.tpg.smp.domain.Address;
import com.tpg.smp.domain.ContactDetails;
import com.tpg.smp.domain.IdentityDetails;
import com.tpg.smp.domain.Name;
import com.tpg.smp.web.model.UserModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationForm implements Serializable {
    @Valid
    private Name name;

    @NotEmpty(message = "{dateofbirth.missing}")
    @Pattern(regexp = "^[0-9]{2}/[0-9]{2}/[1,2][9,0][0-9]{2}$", message = "{dateofbirth.invalid.format}")
    private String dateOfBirth;

    @Valid
    private UserModel userModel;

    private String dateOfRegistration;

    @Valid
    private Address address = new Address();

    @Valid
    private ContactDetails contactDetails = new ContactDetails();

    @NotEmpty(message="{identity.details.missing}")
    private List<IdentityDetails> identityDetails = new ArrayList<>();

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public IdentityDetails getIdentityDetails(int index) {
        return identityDetails.get(index);
    }

    public void setIdentityDetails(int index, IdentityDetails value) {
        this.identityDetails.set(index, value);
    }

    public void addIdentityDetails(IdentityDetails details) {
        identityDetails.add(details);
    }

    public List<IdentityDetails> getIdentityDetails() {
        return identityDetails;
    }
}
