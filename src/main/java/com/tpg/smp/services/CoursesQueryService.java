package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.domain.Course;

public interface CoursesQueryService {
    Optional<Course> findCourseByReferenceNumber(String referenceNo);
}
