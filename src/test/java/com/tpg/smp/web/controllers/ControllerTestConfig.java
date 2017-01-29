package com.tpg.smp.web.controllers;

import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.services.InformationRetrievalService;
import com.tpg.smp.services.registration.StudentRegistrationService;
import com.tpg.smp.web.context.ContentNegotiation;
import com.tpg.smp.web.context.SmpWebConfig;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.tpg.smp.web.context"})
@Import({ContentNegotiation.class, SmpWebConfig.class})
public class ControllerTestConfig {
    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private StudentRegistrationService studentRegistrationService;

    @MockBean
    private InformationRetrievalService informationRetrievalService;

    @Bean
    public AuthenticationService authenticationService() { return authenticationService; }

    @Bean
    public StudentRegistrationService studentRegistrationService() { return studentRegistrationService; }

    @Bean
    public InformationRetrievalService informationRetrievalService() { return informationRetrievalService; }
}
