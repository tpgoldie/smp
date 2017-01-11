package com.tpg.smp.web.pages;

import com.tpg.smp.context.RuntimeConfig;
import com.tpg.smp.web.SeleniumTest;
import com.tpg.smp.web.context.SmpWebAppInitializer;
import com.tpg.smp.web.context.SmpWebConfig;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Profile("intTest")
public class HomePageIntegrationTest {
    @Configuration
    @ComponentScan({"com.tpg.smp.context", "com.tpg.smp.web"})
    static class Config {
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws LifecycleException {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loadHomePage_homePageRequest_expectHomePageToBeLoaded() throws Exception {
        mockMvc.perform(get("/smp/index")
            .header("Content-Type", MediaType.TEXT_HTML_VALUE)
            .header("Accept-Language", "en_GB"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
            .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"))
            .andExpect(model().attribute("welcome",is("Welcome To the University of Warwick")));
    }
}
