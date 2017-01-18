package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.StudentEntities;

public class StudentsData extends UsersData {
    private StudentEntities studentEntities = new StudentEntities();

    public StudentsData() {
        add(new StudentData(authenticatedUsers.ayanaGolding(), userEntities.ayanaGolding(),
            studentEntities.ayanaGolding(), userModels.ayanaGolding()));
    }

    public StudentData getStudent(int index) { return (StudentData) userDataList.get(index); }
}
