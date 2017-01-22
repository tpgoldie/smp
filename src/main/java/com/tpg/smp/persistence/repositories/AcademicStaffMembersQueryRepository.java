package com.tpg.smp.persistence.repositories;

import com.tpg.smp.persistence.entities.AcademicStaffMemberEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AcademicStaffMembersQueryRepository extends QueryRepository<AcademicStaffMemberEntity> {
    List<AcademicStaffMemberEntity> findByDepartmentNameIgnoreCase(String department);
}
