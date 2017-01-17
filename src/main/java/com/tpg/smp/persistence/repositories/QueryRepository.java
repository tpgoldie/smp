package com.tpg.smp.persistence.repositories;

import com.google.common.base.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface QueryRepository<T> extends Repository<T, Long> {
    Optional<T> findById(Long id);
}
