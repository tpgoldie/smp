package com.tpg.smp.persistence.repositories;

import com.google.common.base.Optional;
import com.tpg.smp.persistence.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UsersQueryRepository extends QueryRepository<UserEntity> {
    Optional<UserEntity> findByUsernameAndSecureToken(String username, String secureToken);
}
