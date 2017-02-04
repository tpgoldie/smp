package com.tpg.smp.persistence.repositories;

import com.tpg.smp.persistence.context.DataSourceConfig;
import com.tpg.smp.persistence.context.PersistenceConfig;
import com.tpg.smp.persistence.entities.CourseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@PropertySources(value = {
    @PropertySource(value = "classpath:application-unitTest.properties")
})
@ActiveProfiles({"h2"})
@Sql(
    scripts = {"classpath:sql/schema-h2.sql", "classpath:sql/data-h2.sql"}
)
@TestPropertySource(properties = "spring.profiles.active=unitTest")
public class CoursesQueryRepositoryTest {
    @Configuration
    @EnableConfigurationProperties
    @Import({PersistenceConfig.class, DataSourceConfig.class})
    static class Config {
        @Autowired
        private CoursesQueryRepository coursesQueryRepository;

        @Bean
        public CoursesQueryRepository coursesQueryRepository() {
            return coursesQueryRepository;
        }
    }

    @Autowired
    private CoursesQueryRepository coursesQueryRepository;

    @Test
    @SqlGroup(value = {
        @Sql(
            scripts = {"classpath:sql/maths-dept-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
        ),

        @Sql(
            scripts = {"classpath:sql/clear.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
        )
    })
    public void findByName_courseName_findCourseMatchingTheGivenName() {
        CourseEntity actual = coursesQueryRepository.findByNameIgnoreCase("mathematics").get();

        assertThat(actual, hasProperty("name", is("Mathematics")));
    }
}
