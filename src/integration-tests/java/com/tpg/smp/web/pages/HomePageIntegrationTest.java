package com.tpg.smp.web.pages;

import com.tpg.smp.web.context.SmpWebAppInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
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
    @ComponentScan({"com.tpg.smp.context", "com.tpg.smp.web.context"})
    static class Config {
        @Bean
        public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
            return new TomcatEmbeddedServletContainerFactory();
        }
    }

    @Autowired
    private MockMvc mockMvc;

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
