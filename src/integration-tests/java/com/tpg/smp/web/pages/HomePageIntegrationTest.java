package com.tpg.smp.web.pages;

import com.tpg.smp.web.context.SmpWebAppInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmpWebAppInitializer.class, webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@Profile("intTest")
public class HomePageIntegrationTest {
    @Configuration
    static class Config {
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void handleDefaultHomeRequest_homePageRequest_expectHomePageToBeLoaded() throws Exception {
        performRequest("/smp/");
    }

    private void performRequest(String requestUri) throws Exception {
        String welcomeMsg = "Welcome To the University of Warwick";

        mockMvc.perform(get(requestUri)
            .header("Content-Type", MediaType.TEXT_HTML_VALUE)
            .header("Accept-Language", "en_GB"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
            .andExpect(forwardedUrl("/WEB-INF/views/index.jsp"))
            .andExpect(model().attribute("welcome", is(welcomeMsg)))
            .andExpect(model().attribute("login", is("Please Login")))
            .andExpect(content().contentType(MediaType.TEXT_HTML_VALUE));
    }

    @Test
    public void handleHomePageRequest_homePageRequest_expectHomePageToBeLoaded() throws Exception {
        performRequest("/smp/index");
    }
}
