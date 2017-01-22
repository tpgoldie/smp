package com.tpg.smp.persistence.context;

import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static com.google.common.base.Optional.fromNullable;

@Component
@ConfigurationProperties(prefix = "smp.datasource")
@PropertySources(
    @PropertySource({"classpath:application-${spring.profiles.active}.properties"})
)
public class HibernateProperties {
    private static final String DDL_AUTO_KEY = "hibernate.hbm2ddl.auto";
    private static final String DIALECT_KEY = "hibernate.dialect";
    private static final String GLOBALLY_QUOTED_IDENTIFIERS_KEY = "hibernate.globally_quoted_identifiers";
    private static final String IMPLICIT_NAMING_STRATEGY_KEY = "hibernate.implicit_naming_strategy";
    private static final String PHYSICAL_NAMING_STRATEGY_KEY = "hibernate.physical_naming_strategy";
    private static final String SHOW_SQL_KEY = "hibernate.show_sql";
    private static final String FORMAT_SQL_KEY = "hibernate.format_sql";
    private static final String GENERATE_STATISTICS_KEY = "hibernate.generate_statistics";
    private static final String USE_SECOND_LEVEL_CACHE_KEY = "hibernate.cache.use_second_level_cache";
    private static final String CACHE_PROVIDER_KEY = "hibernate.cache.provider_class";
    private static final String USE_QUERY_CACHE_KEY = "hibernate.cache.use_query_cache";
    private static final String VALIDATION_MODE_KEY = "javax.persistence.validation.mode";

    @Autowired
    private Environment environment;

    public Properties getProperties() {
        return new Properties() {
            {
                setProperty(DDL_AUTO_KEY);
                setProperty(DIALECT_KEY);
                setNamingStrategyProperty("org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
                setProperty(SHOW_SQL_KEY);
                setProperty(FORMAT_SQL_KEY);
                setBooleanProperty(GLOBALLY_QUOTED_IDENTIFIERS_KEY, false);
                setProperty(GENERATE_STATISTICS_KEY);
                setBooleanProperty(USE_SECOND_LEVEL_CACHE_KEY, false);
                setProperty(CACHE_PROVIDER_KEY, "org.hibernate.cache.EhCacheProvider");
                setBooleanProperty(USE_QUERY_CACHE_KEY, false);
                setBooleanProperty(VALIDATION_MODE_KEY, false);
            }

            private synchronized Object setNamingStrategyProperty(String defaultValue) {
                Optional<String> implicitNamingStrategy = fromNullable(environment.getProperty(IMPLICIT_NAMING_STRATEGY_KEY));
                if (implicitNamingStrategy.isPresent()) { return super.setProperty(IMPLICIT_NAMING_STRATEGY_KEY, implicitNamingStrategy.get()); }

                Optional<String> physicalNamingStrategy = fromNullable(environment.getProperty(PHYSICAL_NAMING_STRATEGY_KEY));
                if (physicalNamingStrategy.isPresent()) { return super.setProperty(PHYSICAL_NAMING_STRATEGY_KEY, physicalNamingStrategy.get()); }

                return defaultValue;
            }

            private synchronized Object setBooleanProperty(String key, Boolean defaultValue) {
                Optional<String> value = fromNullable(environment.getProperty(key));
                if (!value.isPresent()) { return defaultValue; }
                return super.setProperty(key, value.get());
            }

            private synchronized Object setProperty(String key) {
                Optional<String> value = fromNullable(environment.getProperty(key));
                if (!value.isPresent()) { return ""; }
                return super.setProperty(key, value.get());
            }
        };
    }
}
