package com.tpg.smp.services.conversion;

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

@RunWith(SpringRunner.class)
public class StudentConverterTest {
    private StudentConverter converter;

    private final UserEntities userEntities = new UserEntities();

    private final StudentEntities studentEntities = new StudentEntities();

    @Before
    public void setUp() {
        UserEntity userEntity = userEntities.getEntity(0);
        converter = new StudentConverter(userEntity);
    }

    @Test
    public void convertEntityToDomainModel_entity_EntityIsConvertedToDomainModel() {
        UserEntity userEntity = userEntities.getEntity(0);

        StudentEntity studentEntity = studentEntities.getEntity(0);

        Student actual = converter.convert(studentEntity);

        new AssertStudent(actual).matches(userEntity, studentEntity);
    }
}
