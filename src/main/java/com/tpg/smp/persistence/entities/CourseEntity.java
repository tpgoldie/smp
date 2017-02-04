package com.tpg.smp.persistence.entities;

import javax.persistence.*;

@Entity(name = "course")
@Table(name = "T_COURSES", schema = "SMP")
public class CourseEntity extends BaseEntity {
    @Column(name = "COURSE_NO")
    private String courseNumber;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private DepartmentEntity department;

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
