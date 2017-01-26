package com.tpg.smp.persistence.repositories;

import com.tpg.smp.persistence.context.DataSourceConfig;
import com.tpg.smp.persistence.context.PersistenceConfig;
import com.tpg.smp.persistence.entities.AcademicStaffMemberEntity;
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
import java.util.Optional;

import static com.tpg.smp.domain.AcademicStaffMemberType.AffiliateLecturer;
import static com.tpg.smp.domain.AcademicStaffMemberType.Professor;
import static com.tpg.smp.domain.AcademicStaffMemberType.ResearchFellow;
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
    public void findByDepartment_department_findAllMembersOfTheGivenDepartmentSingleResult() {
        List<AcademicStaffMemberEntity> actualList = academicStaffMembersQueryRepository.findByDepartmentNameIgnoreCase("fashion and design");
        assertThat(actualList.size(), is(1));

        AcademicStaffMemberEntity actual = actualList.get(0);

        assertThat(actual.getName(), hasProperty("firstName", is("Vivenne")));
        assertThat(actual.getName(), hasProperty("lastName", is("Westwood")));

        assertThat(actual.getAcademicStaffMemberType(), hasProperty("description", is(AffiliateLecturer.getDescription())));
        assertThat(actual.getDepartment(), hasProperty("name", is("Fashion And Design")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/maths-dept-data.sql")
    public void findByDepartment_department_findAllMembersOfTheGivenDepartmentMultipleResults() {
        List<AcademicStaffMemberEntity> actualList = academicStaffMembersQueryRepository.findByDepartmentNameIgnoreCase("Mathematics");
        assertThat(actualList.size(), is(2));

        Optional<AcademicStaffMemberEntity> ian = findByName(actualList, "Ian", "Stewart");
        assertThat(ian.isPresent(), is(true));
        assertThat(ian.get().getDepartment(), hasProperty("name", is("Mathematics")));
        assertThat(ian.get().getAcademicStaffMemberType(), hasProperty("description", is(Professor.getDescription())));

        Optional<AcademicStaffMemberEntity> richard = findByName(actualList, "Richard", "Adams");
        assertThat(richard.isPresent(), is(true));
        assertThat(richard.get().getDepartment(), hasProperty("name", is("Mathematics")));
        assertThat(richard.get().getAcademicStaffMemberType(), hasProperty("description", is(ResearchFellow.getDescription())));
    }

    private Optional<AcademicStaffMemberEntity> findByName(List<AcademicStaffMemberEntity> actualList, String firstName, String lastName) {
        return actualList.stream().filter(m -> m.getName().getFirstName().equals(firstName) &&
            m.getName().getLastName().equals(lastName)).findFirst();
    }
}
