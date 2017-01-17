package com.tpg.smp.persistence.repositories;

import com.tpg.smp.persistence.entities.TeachingStaffMemberEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TeachingStaffMembersQueryRepository extends QueryRepository<TeachingStaffMemberEntity> {
}
