package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.data.CourseData;
import com.tpg.smp.data.CoursesData;
import com.tpg.smp.data.DepartmentsData;
import com.tpg.smp.domain.Course;
import com.tpg.smp.persistence.repositories.CoursesQueryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CoursesQueryServiceTest {
    @Configuration
    static class Config {
        @MockBean
        private CoursesQueryRepository coursesQueryRepository;

        @Bean
        public CoursesQueryService coursesQueryService() { return new CoursesQueryHandler(coursesQueryRepository); }
    }

    CourseData courseData = new CoursesData(new DepartmentsData()).getCourseData(0);

    @Autowired
    private CoursesQueryRepository coursesQueryRepository;

    @Autowired
    private CoursesQueryService coursesQueryService;

    @Test
    public void findByCourseNumber_courseNumber_courseToBeFound() {
        Optional<Course> actual = coursesQueryService.findCourseByReferenceNumber(courseData.getDomainModel().getCourseNumber());

    }
}
