package com.tpg.smp.persistence.context;


import com.tpg.smp.persistence.entities.PersonEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.tpg.smp.persistence.repositories"})
@EnableJpaRepositories
@EntityScan(basePackageClasses = {PersonEntity.class})
public class PersistenceConfig {
}
