package com.tpg.smp.services;

import com.tpg.smp.domain.Course;
import com.tpg.smp.persistence.entities.CourseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class AssertCourse extends AssertDomainModel<Course> {
    public AssertCourse(Course domainModel) {
        super(domainModel);
    }

    public void matches(CourseEntity courseEntity) {
        assertThat(courseEntity, hasProperty("name", is(domainModel.getName())));
        assertThat(courseEntity, hasProperty("courseNumber", is(domainModel.getCourseNumber())));
        assertThat(courseEntity, hasProperty("description", is(domainModel.getDescription())));
    }
}
