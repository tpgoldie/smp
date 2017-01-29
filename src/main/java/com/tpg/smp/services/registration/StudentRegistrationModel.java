package com.tpg.smp.services.registration;

import com.google.common.base.Optional;
import com.tpg.smp.domain.*;
import com.tpg.smp.services.conversion.ToDateTimeConverter;
import com.tpg.smp.web.controllers.forms.StudentRegistrationForm;
import com.tpg.smp.web.model.UserModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.joda.time.DateTime;

import java.util.List;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

public class StudentRegistrationModel {
    private final Name name;
    private final UserModel userModel;
    private final GenderType gender;
    private final Address address;
    private final DateTime dateOfBirth;
    private final DateTime dateOfRegistration;
    private final ContactDetails contactDetails;
    private final List<IdentityDetails> identityDetails;

    public StudentRegistrationModel(StudentRegistrationForm form) {
        name = form.getName();

        gender = findGenderTypeBySymbol(form.getGender()).get();

        userModel = form.getUserModel();

        address = form.getAddress();

        ToDateTimeConverter toDateTimeConverter = new ToDateTimeConverter();

        dateOfBirth = toDateTimeConverter.convert(form.getDateOfBirth());

        dateOfRegistration = toDateTimeConverter.convert(form.getDateOfRegistration());

        contactDetails = form.getContactDetails();

        identityDetails = form.getIdentityDetails();
    }

    private Optional<GenderType> findGenderTypeBySymbol(String symbol) {
        java.util.Optional<GenderType> found = GenderType.TypedValues().stream().filter(g -> g.getSymbol().equalsIgnoreCase(symbol)).findAny();

        return found.isPresent() ? of(found.get()) : absent();
    }

    public Name getName() {
        return name;
    }

    public GenderType getGender() { return gender; }

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
