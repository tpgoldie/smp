package com.tpg.smp.services.conversion;

import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class StudentConverter implements Converter<StudentEntity, Student> {
    private final UserEntity userEntity;

    public StudentConverter(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Student convert(StudentEntity source) {
        return new Student(userEntity.getUsername(), source.getName().getFirstName(),
            source.getName().getLastName(), source.getStudentNumber());
    }
}
