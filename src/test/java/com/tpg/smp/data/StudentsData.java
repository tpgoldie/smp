package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.StudentEntities;

public class StudentsData extends UsersData {
    private StudentEntities studentEntities = new StudentEntities();

    public StudentsData() {
        userDataList.add(new StudentData(userEntities.ayanaGolding(), studentEntities.ayanaGolding(), userModels.ayanaGolding()));
    }

    public StudentData getTestStudent(int index) { return (StudentData) userDataList.get(index); }
}
