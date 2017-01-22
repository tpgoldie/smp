package com.tpg.smp.persistence.repositories;

import com.tpg.smp.persistence.context.DataSourceConfig;
import com.tpg.smp.persistence.context.PersistenceConfig;
import com.tpg.smp.persistence.entities.AcademicStaffMemberEntity;
import com.tpg.smp.persistence.entities.udts.AcademicStaffMemberType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@PropertySources(value = {
    @PropertySource(value = "classpath:application-h2.properties")
})
@ActiveProfiles({"h2"})
@Sql(
    scripts = {"classpath:sql/schema-h2.sql", "classpath:sql/data-h2.sql"}
)
@TestPropertySource(properties = "spring.profiles.active=h2")
public class AcademicStaffMembersQueryRepositoryTest {
    @Configuration
    @EnableConfigurationProperties
    @Import({PersistenceConfig.class, DataSourceConfig.class})
    static class Config {
        @Autowired
        private AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository;

        @Bean
        public AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository() {
            return academicStaffMembersQueryRepository;
        }
    }

    @Autowired
    private AcademicStaffMembersQueryRepository academicStaffMembersQueryRepository;

    @Test
    public void findByDepartment_department_findAllMembersOfTheGivenDepartment() {
        List<AcademicStaffMemberEntity> actualist = academicStaffMembersQueryRepository.findByDepartmentNameIgnoreCase("fashion and design");
        assertThat(actualist.size(), is(1));

        AcademicStaffMemberEntity actual = actualist.get(0);

        assertThat(actual.getName(), hasProperty("firstName", is("Vivenne")));
        assertThat(actual.getName(), hasProperty("lastName", is("Westwood")));

        assertThat(actual.getAcademicStaffMemberType(), hasProperty("description", is(AcademicStaffMemberType.AffiliateLecturer.getDescription())));
        assertThat(actual.getDepartment(), hasProperty("name", is("Fashion And Design")));
    }
}
