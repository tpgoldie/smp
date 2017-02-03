package com.tpg.smp.persistence.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface ManagementRepository<T> extends Repository<T, Long> {
    void save(T entity);
}
