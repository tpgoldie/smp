package com.tpg.smp.data;

import com.tpg.smp.domain.Name;
import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.StudentEntities;
import com.tpg.smp.persistence.entities.StudentEntity;

public class StudentsData extends UsersData {
    private StudentEntities studentEntities = new StudentEntities();

    public StudentsData() {
        StudentEntity entity = studentEntities.michaelDanque();
        entity.setUser(userEntities.michaelDanque());

        Name name = new Name(entity.getName().getFirstName(), entity.getName().getLastName());

        Student domainModel = new Student(name, entity.getUser().getUsername(), entity.getStudentNumber());

        add(new StudentData(authenticatedUsers.michaelDanque(), userEntities.michaelDanque(),
            studentEntities.michaelDanque(), userModels.michaelDanque(), domainModel));
    }

    public StudentData getStudent(int index) { return (StudentData) userDataList.get(index); }
}
