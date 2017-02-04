package com.tpg.smp.persistence.repositories;

import com.google.common.base.Optional;
import com.tpg.smp.persistence.entities.CourseEntity;

public interface CoursesQueryRepository extends QueryRepository<CourseEntity> {
    Optional<CourseEntity> findByNameIgnoreCase(String courseNo);
}
