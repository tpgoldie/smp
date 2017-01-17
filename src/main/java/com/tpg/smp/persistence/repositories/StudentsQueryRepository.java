package com.tpg.smp.persistence.repositories;

import com.tpg.smp.persistence.entities.StudentEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface StudentsQueryRepository extends QueryRepository<StudentEntity> {
}
