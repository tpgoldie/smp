package com.tpg.smp.web.ui.actions;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.tpg.smp.persistence.context.PersistenceConfig;
import com.tpg.smp.web.SmpWebIntegrationTest;
import com.tpg.smp.web.ui.pages.IndexPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

public class LoginIntegrationTest extends SmpWebIntegrationTest {
    @Configuration
    @Import(PersistenceConfig.class)
    static class Config {
        @Autowired
        private WebApplicationContext wac;

        @Bean
        public DataSourceProperties dataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean
        public MockMvc mockMvc() {
            return MockMvcBuilders.webAppContextSetup(wac).build();
        }

        @Bean
        public WebDriver webDriver() {
            return MockMvcHtmlUnitDriverBuilder.mockMvcSetup(mockMvc()).contextPath("/smp").build();
        }
    }

    @LocalServerPort
    private int port;

    @Autowired
    private WebDriver webDriver;

    private IndexPage indexPage;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/smp/", port);

        webDriver.get(url);

        indexPage = PageFactory.initElements(webDriver, IndexPage.class);
    }

    @Test
    public void handleLoginForValidUser_validUser_validUserIsLoggedIn() throws IOException {
        indexPage.setUsername("iastewart");
        indexPage.setSecureToken("22345");

        indexPage.login();
    }
}
