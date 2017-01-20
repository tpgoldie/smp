package com.tpg.smp.services.conversion;

import com.tpg.smp.domain.Name;
import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntity;

public class StudentConverter extends PersonConverter<StudentEntity, Student> {
    public StudentConverter(UserEntity userEntity) {
        super(userEntity);
    }

    @Override
    public Student convert(StudentEntity source) {
        return new Student(new Name(source.getName().getFirstName(), source.getName().getLastName()),
            userEntity.getUsername(), source.getStudentNumber());
    }
}
