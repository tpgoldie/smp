package com.tpg.smp.persistence.context;


import com.tpg.smp.persistence.entities.BaseEntity;
import com.tpg.smp.persistence.repositories.QueryRepository;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import static javax.persistence.ValidationMode.AUTO;

@Configuration
@ComponentScan(basePackages = {"com.tpg.smp.persistence.context"})
@EnableJpaRepositories(basePackageClasses = {QueryRepository.class})
@EnableConfigurationProperties
@EntityScan(basePackageClasses = {BaseEntity.class})
public class PersistenceConfig {
    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean
    public DataSource dataSource() {
        DataSourceProperties dataSourceProperties = dataSourceConfig.getDataSourceProperties();

        return dataSourceProperties.initializeDataSourceBuilder()
            .driverClassName(dataSourceProperties.getDriverClassName())
            .username(dataSourceProperties.getUsername())
            .password(dataSourceProperties.getPassword())
            .url(dataSourceProperties.getUrl())
            .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);

        factory.setPersistenceUnitName(dataSourceConfig.getPersistenceName());

        factory.setPersistenceProvider(new HibernatePersistenceProvider());
        factory.setJpaVendorAdapter(jpaVendorAdapter());

        factory.setJpaDialect(new HibernateJpaDialect());
        factory.setPackagesToScan("com.tpg.smp.persistence.entities");
        factory.setJpaProperties(hibernateProperties.getProperties());
        factory.setValidationMode(AUTO);

        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return factory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setShowSql(true);

        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() { return new JpaTransactionManager(); }
}
