package com.tpg.smp.services.registration;

import com.tpg.smp.domain.Address;
import com.tpg.smp.domain.ContactDetails;
import com.tpg.smp.domain.IdentityDetails;
import com.tpg.smp.domain.Name;
import com.tpg.smp.services.conversion.ToDateTimeConverter;
import com.tpg.smp.web.controllers.forms.StudentRegistrationForm;
import com.tpg.smp.web.model.UserModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.joda.time.DateTime;

import java.util.List;

public class StudentRegistrationModel {
    private final Name name;
    private final UserModel userModel;
    private final Address address;
    private final DateTime dateOfBirth;
    private final DateTime dateOfRegistration;
    private final ContactDetails contactDetails;
    private final List<IdentityDetails> identityDetails;

    private final ToDateTimeConverter toDateTimeConverter = new ToDateTimeConverter();

    public StudentRegistrationModel(StudentRegistrationForm form) {
        name = form.getName();

        userModel = form.getUserModel();

        address = form.getAddress();

        dateOfBirth = toDateTimeConverter.convert(form.getDateOfBirth());
        dateOfRegistration = toDateTimeConverter.convert(form.getDateOfRegistration());

        contactDetails = form.getContactDetails();
        identityDetails = form.getIdentityDetails();
    }

    public Name getName() {
        return name;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public Address getAddress() {
        return address;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public DateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public List<IdentityDetails> getIdentityDetails() {
        return identityDetails;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (!(obj instanceof StudentRegistrationModel)) { return false; }

        StudentRegistrationModel that = (StudentRegistrationModel) obj;

        return new EqualsBuilder()
            .append(that.name, this.name)
            .append(that.userModel, this.userModel)
            .append(that.address, this.address)
            .append(that.dateOfBirth, this.dateOfBirth)
            .append(that.dateOfRegistration, this.dateOfRegistration)
            .append(that.contactDetails, this.contactDetails)
            .append(that.identityDetails, this.identityDetails)
            .isEquals();
    }
}
