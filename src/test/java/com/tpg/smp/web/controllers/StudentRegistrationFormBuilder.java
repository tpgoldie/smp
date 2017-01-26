package com.tpg.smp.web.controllers;

import com.tpg.smp.domain.*;
import com.tpg.smp.services.conversion.FromDateTimeConverter;
import com.tpg.smp.web.controllers.forms.StudentRegistrationForm;
import com.tpg.smp.web.model.UserModel;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static com.tpg.smp.domain.IdentityType.BritishDrivingLicence;

public class StudentRegistrationFormBuilder {
    private final FromDateTimeConverter fromDateTimeConverter = new FromDateTimeConverter();

    private Name name;
    private UserModel userModel;
    private DateTime dateOfBirth;
    private DateTime dateOfRegistration;

    private Address address;
    private ContactDetails contactDetails = new ContactDetails();

    private List<IdentityDetails> identityDetails = new ArrayList<>();

    private String courseReferenceNumber;

    public StudentRegistrationFormBuilder name(String firstName, String lastName) {
        this.name = new Name(firstName, lastName);
        return this;
    }

    public StudentRegistrationFormBuilder userModel(UserModel userModel) {
        this.userModel = userModel;
        return this;
    }

    public StudentRegistrationFormBuilder dateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public StudentRegistrationFormBuilder dateOfRegistration(DateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
        return this;
    }

    public StudentRegistrationFormBuilder address(String lineOne, String lineTwo, String region, Country country, String postCode) {
        this.address = new Address();

        this.address.addLine(lineOne);
        this.address.addLine(lineTwo);
        this.address.setRegion(region);
        this.address.setPostCode(postCode);
        this.address.setCountry(country.getName());

        return this;
    }

    public StudentRegistrationFormBuilder contactDetails(String... contactNumbers) {
        for (String contactNumber : contactNumbers) {
            this.contactDetails.addContactNumber(contactNumber);
        }

        return this;
    }

    public StudentRegistrationFormBuilder dateOfRegistration(List<IdentityDetails> identityDetails) {
        this.identityDetails = identityDetails;
        return this;
    }

    public StudentRegistrationFormBuilder emailAddress(String emailAddress) {
        this.contactDetails.setMainEmailAddress(emailAddress);
        return this;
    }

    public StudentRegistrationFormBuilder identityDetails(List<IdHolder> idHolders) {
        idHolders.forEach(holder -> addIdentityDetails(holder));
        return this;
    }

    private void addIdentityDetails(IdHolder idHolder) {
        IdentityDetails id = new IdentityDetails();

        id.setIdentityType(idHolder.getIdentityType().getDescription());
        id.setIdentityNumber(idHolder.getValue());

        this.identityDetails.add(id);
    }

    public StudentRegistrationFormBuilder courseReferenceNumber(String referenceNo) {
        this.courseReferenceNumber = referenceNo;
        return this;
    }

    public StudentRegistrationForm build() {
        StudentRegistrationForm form = new StudentRegistrationForm();

        form.setName(name);
        form.setAddress(address);

        if (dateOfBirth != null) {
            form.setDateOfBirth(fromDateTimeConverter.convert(dateOfBirth));
        }

        form.setDateOfRegistration(fromDateTimeConverter.convert(dateOfRegistration));

        form.setContactDetails(contactDetails);

        form.setUserModel(userModel);
        identityDetails.forEach(id -> form.addIdentityDetails(id));

        form.setCourseReferenceNumber(courseReferenceNumber);

        return form;
    }

    public static class IdHolder {
        private final IdentityType identityType;
        private final String value;

        public IdHolder(IdentityType identityType, String value) {
            this.identityType = identityType;
            this.value = value;
        }

        public IdentityType getIdentityType() {
            return identityType;
        }

        public String getValue() {
            return value;
        }
    }
}
