package com.tpg.smp.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "department")
@Table(name = "T_DEPARTMENTS", schema = "SMP")
public class DepartmentEntity extends BaseEntity {
    @Column(name = "DEPT_NUMBER")
    private String departmentNumber;

    private String name;

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
