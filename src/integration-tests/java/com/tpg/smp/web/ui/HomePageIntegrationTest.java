package com.tpg.smp.web.ui;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tpg.smp.web.SmpWebIntegrationTest;
import com.tpg.smp.web.context.SmpWebAppInitializer;
import com.tpg.smp.web.ui.pages.IndexPage;
import com.tpg.smp.web.ui.pages.IndexPageAssert;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

public class HomePageIntegrationTest extends SmpWebIntegrationTest {
    @Configuration
    static class Config {
        @Autowired
        private WebApplicationContext wac;

        @Bean
        public WebClient webClient() {
            return MockMvcWebClientBuilder.webAppContextSetup(wac).build();
        }

        @Bean
        public HtmlUnitDriver htmlUnitDriver() {
            return MockMvcHtmlUnitDriverBuilder.webAppContextSetup(wac).build();
        }
    }

    @Autowired
    private WebClient webClient;

    @Autowired
    private WebDriver webDriver;

    @LocalServerPort
    private int port;

    private IndexPage indexPage;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/smp/index", port);
        webDriver.get(url);

        indexPage = PageFactory.initElements(webDriver, IndexPage.class);
    }

    @Test
    public void loadHomePage_homePageRequest_expectHomePageIsLoaded() throws IOException {
        IndexPageAssert assertation = indexPage.assertThat();
        assertation.hasWelcomeMessage();
    }
}
