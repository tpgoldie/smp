package com.tpg.smp.web.controllers;

import com.tpg.smp.auth.AuthenticationService;
import com.tpg.smp.web.context.SmpWebConfig;
import com.tpg.smp.web.context.ViewResolution;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

        @Bean
        public AuthenticationService authenticationService() { return Mockito.mock(AuthenticationService.class); }
    }

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    protected AuthenticationService authenticationService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}
