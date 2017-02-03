package com.tpg.smp.persistence.entities;

import com.tpg.smp.domain.ContactDetailType;
import com.tpg.smp.persistence.entities.convertibles.ContactDetailTypeConvertible;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "contact")
@Table(name = "CONTACT_DETAILS", schema = "SMP")
public class ContactDetailsEntity extends BaseEntity {
    @Column(name = "CONTACT_DETAIL")
    private String detail;

    @Column(name = "CONTACT_DETAIL_TYPE")
    @Convert(converter = ContactDetailTypeConvertible.class)
    private ContactDetailType detailType;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ContactDetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(ContactDetailType detailType) {
        this.detailType = detailType;
    }
}
