package com.tpg.smp.services;

import com.google.common.base.Optional;
import com.tpg.smp.domain.Course;
import com.tpg.smp.persistence.repositories.CoursesQueryRepository;
import org.springframework.stereotype.Service;

@Service
public class CoursesQueryHandler implements CoursesQueryService {
    private CoursesQueryRepository coursesQueryRepository;

    public CoursesQueryHandler(CoursesQueryRepository coursesQueryRepository) {
        this.coursesQueryRepository = coursesQueryRepository;
    }

    @Override
    public Optional<Course> findCourseByReferenceNumber(String referenceNo) {
        return null;
    }
}
