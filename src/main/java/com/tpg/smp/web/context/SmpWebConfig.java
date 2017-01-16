package com.tpg.smp.web.context;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.tpg.smp.web"})
@Import({ContentNegotiation.class})
public class SmpWebConfig extends WebMvcConfigurerAdapter {
    private static final String WEBAPP_NAME = "smp";

    private static final String WEBAPP_PREFIX = String.format("/%s", WEBAPP_NAME);

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.TEXT_HTML);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    @Order(1)
    public ServletRegistrationBean dispatcherServletRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.setName(WEBAPP_NAME);
        registration.addUrlMappings(String.format("%s/*", WEBAPP_PREFIX));

        return registration;
    }
}
