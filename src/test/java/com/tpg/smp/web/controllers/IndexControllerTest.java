package com.tpg.smp.web.controllers;

import com.tpg.smp.web.context.SmpWebConfig;
import com.tpg.smp.web.controllers.expectations.HandleIndexRequestExpectation;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest({IndexController.class})
@Profile("unitTest")
public class IndexControllerTest extends BaseControllerTest {
    @Configuration
    @ComponentScan(basePackages = {"com.tpg.smp.web.context"})
    @Import(SmpWebConfig.class)
    static class Config {
    }

    @Test
    public void handleDefaultRequest_defaultRequest_homePageIsDisplayed() throws Exception {
        performHomeRequest("/smp/");
    }

    @Test
    public void handleIndexRequest_indexRequest_homePageIsDisplayed() throws Exception {
        performHomeRequest("/smp/index");
    }

    private void performHomeRequest(String requestAction) throws Exception {
        ResultActions resultActions = mockMvc.perform(get(requestAction)
                .header("Content-Type", MediaType.TEXT_HTML_VALUE)
                .header("Accept-Language", "en_GB"));

        HandleIndexRequestExpectation expectation = new HandleIndexRequestExpectation(resultActions,
                new HandleIndexRequestExpectation.WelcomeExpectedAttribute("Welcome To the University of Warwick"),
                new HandleIndexRequestExpectation.LoginExpectedAttribute("Please Login"));

        expectation.met();
    }
}
