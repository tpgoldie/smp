package com.tpg.smp.services.conversion;

import com.tpg.smp.data.StudentData;
import com.tpg.smp.data.StudentsData;
import com.tpg.smp.domain.Student;
import com.tpg.smp.persistence.entities.StudentEntities;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntities;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.services.AssertStudent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class StudentConverterTest {
    private StudentData studentData = new StudentsData().getStudent(0);

    @Test
    public void convertEntityToDomainModel_entity_EntityIsConvertedToDomainModel() {
        UserEntity userEntity = studentData.getUserEntity();

        StudentEntity studentEntity = studentData.getStudentEntity();

        StudentConverter converter = new StudentConverter(userEntity);

        Student actual = converter.convert(studentEntity);

        new AssertStudent(actual).matches(userEntity, studentEntity);
    }
}
