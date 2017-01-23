package com.tpg.smp.web.context;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketMessagingAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class, WebClientAutoConfiguration.class,
        WebSocketAutoConfiguration.class, WebSocketMessagingAutoConfiguration.class,
        SpringDataWebAutoConfiguration.class, MultipartAutoConfiguration.class, HttpEncodingAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, DataSourceAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
public class SmpWebAppInitializer extends SpringBootServletInitializer {
    private static final String WEBAPP_NAME = "smp";

    private static final String DISPLAY_NAME = "Warwick University Portal";

    private static final String SERVLET_NAME = "smp";

    private static final String WEBAPP_PREFIX = String.format("/%s", WEBAPP_NAME);

    private static final String MAPPING_CONTEXT = String.format("%s/*", WEBAPP_PREFIX);

    private static final String THROW_EXCEPTION_FOR_NO_HANDLER_KEY = "throwExceptionIfNoHandlerFound";

    private static final int LOAD_ON_STARTUP = 2;

    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(SmpWebAppInitializer.class).bannerMode(Banner.Mode.OFF);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
//        annotationConfigWebApplicationContext.register(SmpWebConfig.class);
//
//        servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));

        AnnotationConfigWebApplicationContext dispatcherServlet = theAnnotationWebContext();
        dispatcherServlet.register(SmpWebConfig.class);

        ServletRegistration.Dynamic registration = servletContext.addServlet(SERVLET_NAME,
                dispatcherServlet(dispatcherServlet));

        registration.setLoadOnStartup(LOAD_ON_STARTUP);

        registration.addMapping(MAPPING_CONTEXT);
        registration.setInitParameter(THROW_EXCEPTION_FOR_NO_HANDLER_KEY, "true");
    }

    @Bean
    public DispatcherServlet dispatcherServlet(AnnotationConfigWebApplicationContext webAppContext) {
        return new DispatcherServlet(webAppContext);
    }

    @Bean
    public AnnotationConfigWebApplicationContext theAnnotationWebContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setDisplayName(DISPLAY_NAME);
        return context;
    }
}
