package com.tpg.smp.web.controllers;

import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.data.PasswordGenerator;
import com.tpg.smp.services.conversion.DateMonthYearFormat;
import com.tpg.smp.web.context.SmpWebConfig;
import com.tpg.smp.web.context.ViewResolution;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BaseControllerTest.Config.class)
@Profile("unitTest")
@TestPropertySource(properties = {"spring.profiles.active=unitTest"})
@WebAppConfiguration
public abstract class BaseControllerTest {
    @Configuration
    @Import({SmpWebConfig.class, ViewResolution.class})
    static class Config {
        @Autowired
        private MessageSource messageSource;

        @Autowired
        private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

        @Bean
        public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() { return jackson2HttpMessageConverter; }

        @Bean
        public PasswordGenerator passwordGenerator() {
            return new PasswordGenerator();
        }

        @Bean
        public AuthenticationService authenticationService() { return mock(AuthenticationService.class); }
    }

    protected static final DateMonthYearFormat DATE_MONTH_YEAR_FORMAT = DateMonthYearFormat.getDateMonthYearFormat();

    protected static final DateTime DATE_OF_REGISTRATION = new DateTime();

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    PasswordGenerator passwordGenerator;

    @Autowired
    protected MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

    @Autowired
    protected AuthenticationService authenticationService;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(wac).build();
    }
}
