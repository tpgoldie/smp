package com.tpg.smp.data;

import com.tpg.smp.persistence.entities.StudentEntities;

public class StudentsData extends UsersData {
    private StudentEntities studentEntities = new StudentEntities();

    public StudentsData() {
        add(new StudentData(authenticatedUsers.michaelDanque(), userEntities.michaelDanque(),
            studentEntities.michaelDanque(), userModels.michaelDanque()));
    }

    public StudentData getStudent(int index) { return (StudentData) userDataList.get(index); }
}
