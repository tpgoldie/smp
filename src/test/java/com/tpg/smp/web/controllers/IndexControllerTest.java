package com.tpg.smp.web.controllers;

import com.tpg.smp.web.context.SmpWebConfig;
import com.tpg.smp.web.controllers.expectations.HandleIndexRequestExpectation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {IndexControllerTest.IndexControllerTestConfig.class}, loader = WebDelegatingSmartContextLoader.class)
@WebAppConfiguration
@Profile("unitTest")
public class IndexControllerTest {
    @Configuration
    @ComponentScan(basePackages = {"com.tpg.smp.web.context"})
    @Import(SmpWebConfig.class)
    static class IndexControllerTestConfig {

    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void handleIndexRequest_indexRequest_homePageIsDisplayed() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/smp/index")
                .header("Accept-Language", "en_GB"));

        HandleIndexRequestExpectation expectation = new HandleIndexRequestExpectation(resultActions,
                new HandleIndexRequestExpectation.WelcomeExpectedModelAttribute("Welcome To the University of Warwick"));

        expectation.met();
    }
}
