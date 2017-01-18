package com.tpg.smp.persistence.repositories;

import com.tpg.smp.persistence.entities.AdministrativeStaffMemberEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AdministrativeStaffMembersQueryRepository extends QueryRepository<AdministrativeStaffMemberEntity> {
}
