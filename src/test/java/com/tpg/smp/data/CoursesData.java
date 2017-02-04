package com.tpg.smp.data;

import com.tpg.smp.domain.Course;
import com.tpg.smp.domain.Department;

import java.util.Optional;

public class CoursesData extends TestData<CourseData> {
    public CoursesData(DepartmentsData departmentsData) {
        Optional<DepartmentData> found = departmentsData.stream().filter(dept -> dept.getDomainModel().getName().equalsIgnoreCase("mathematics")).findFirst();
        if (!found.isPresent()) { return; }

        add(createCourse(found.get().getDomainModel(), "Mathematics", "MATH_001"));
    }

    private CourseData createCourse(Department department, String name, String referenceNo) {
        Course course = new Course();

        course.setDepartment(department);
        course.setName(name);
        course.setCourseNumber(referenceNo);

        return new CourseData(course);
    }

    public CourseData getCourseData(int index) { return get(index); }
}
