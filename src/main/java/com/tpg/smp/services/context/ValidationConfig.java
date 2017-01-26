package com.tpg.smp.services.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ValidatorFactory;
import javax.validation.Validator;

@Configuration
public class ValidationConfig {
    @Autowired
    private MessageSource messageSource;

    @Bean
    public ValidatorFactory validatorFactory() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource);
        return factory;
    }

    @Bean
    public Validator validator() {
        return validatorFactory().getValidator();
    }
}
