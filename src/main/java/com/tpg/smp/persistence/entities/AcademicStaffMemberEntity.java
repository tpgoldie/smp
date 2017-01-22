package com.tpg.smp.persistence.entities;

import com.tpg.smp.persistence.entities.convertibles.AcademicStaffMemberTypeConvertible;
import com.tpg.smp.persistence.entities.udts.AcademicStaffMemberType;

import javax.persistence.*;

@Entity(name = "academicStaffMember")
@Table(name = "T_TEACHING_STAFF", schema = "SMP")
@AttributeOverrides({
    @AttributeOverride(name = "uniqueRegistrationNumber", column = @Column(name = "STAFF_MEMBER_NUMBER"))
})
public class AcademicStaffMemberEntity extends PersonEntity {
    @Column(name = "TEACHING_STAFF_MEMBER_TYPE")
    @Convert(converter = AcademicStaffMemberTypeConvertible.class)
    private AcademicStaffMemberType academicStaffMemberType;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private DepartmentEntity department;

    public String getStaffMemberNumber() {
        return getIdentificationNumber();
    }

    public void setStaffMemberNumber(String staffMemberNo) {
        setIdentificationNumber(staffMemberNo);
    }

    public AcademicStaffMemberType getAcademicStaffMemberType() {
        return academicStaffMemberType;
    }

    public void setAcademicStaffMemberType(AcademicStaffMemberType academicStaffMemberType) {
        this.academicStaffMemberType = academicStaffMemberType;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
