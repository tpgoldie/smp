package com.tpg.smp.web.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"com.tpg.smp.web"})
public class SmpWebConfig extends WebMvcConfigurerAdapter {
    private static final String WEBAPP_NAME = "smp";

    static final String WEBAPP_PREFIX = String.format("/%s", WEBAPP_NAME);

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
