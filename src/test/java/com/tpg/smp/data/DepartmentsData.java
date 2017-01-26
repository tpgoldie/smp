package com.tpg.smp.data;

import com.tpg.smp.domain.Department;

public class DepartmentsData extends TestData<DepartmentData> {
    public DepartmentsData() {
        add(createDepartment("Mathematics", "101"));
    }

    private DepartmentData createDepartment(String name, String deptNo) {
        Department department = new Department();

        department.setName(name);
        department.setDepartmentNumber(deptNo);

        return new DepartmentData(department);
    }
}
