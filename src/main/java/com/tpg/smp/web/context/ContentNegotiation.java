package com.tpg.smp.web.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
@Import(ViewResolution.class)
public class ContentNegotiation {
    @Autowired
    private ViewResolver jspViewResolver;

    @Bean
    public ViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();

        // Define all possible view resolvers
        List<ViewResolver> resolvers = new ArrayList<>();

        resolvers.add(jspViewResolver);

        resolver.setViewResolvers(resolvers);

        return resolver;
    }
//
//    @Bean
//    public ContentNegotiationManagerFactoryBean contentNegotiationManager() {
//        ContentNegotiationManagerFactoryBean factory = new ContentNegotiationManagerFactoryBean();
//        factory.setFavorParameter(true);
//        factory.setFavorPathExtension(false);
//        factory.setIgnoreAcceptHeader(false);
//        factory.setDefaultContentType(MediaType.APPLICATION_JSON_UTF8);
//
//        factory.setMediaTypes(mediaTypes());
//        return factory;
//    }
//
//    private Properties mediaTypes() {
//        Properties properties = new Properties();
//        properties.setProperty("json", MediaType.APPLICATION_JSON_UTF8_VALUE);
//        properties.setProperty("html", MediaType.TEXT_HTML_VALUE);
//
//        return properties;
//    }
}
