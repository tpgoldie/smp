package com.tpg.smp.web.pages;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tpg.smp.web.context.SmpWebAppInitializer;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmpWebAppInitializer.class, webEnvironment = RANDOM_PORT)
@Profile("intTest")
public class HomeViewIntegrationTest {
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
    private HtmlUnitDriver unitDriver;

    @LocalServerPort
    private int port;

    @Test
    public void loadHomePage_homePageRequest_expectHomePageIsLoaded() throws IOException {
        String url = String.format("http://localhost:%d/smp/index", port);

        unitDriver.get(url);

        WebElement element = unitDriver.findElement(By.id("welcome-id"));
        assertThat(element, hasProperty("text", is("Welcome To the University of Warwick")));

        webClient.addRequestHeader("Accept-Language", "en");
        webClient.addRequestHeader("Content-Type", TEXT_HTML_VALUE);

        HtmlPage page = webClient.getPage(url);

       Assertions.assertThat(page.getBody().getTextContent()).contains("Welcome To the University of Warwick");
    }
}
