package com.tpg.smp.web.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.tpg.smp.web"})
public class SmpWebConfig extends WebMvcConfigurationSupport {
    private static final String WEBAPP_NAME = "smp";

    private static final String WEBAPP_PREFIX = String.format("/%s", WEBAPP_NAME);

    @Autowired
    private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter);
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
