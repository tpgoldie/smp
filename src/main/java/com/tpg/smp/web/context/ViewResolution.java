package com.tpg.smp.web.context;

import com.tpg.smp.web.controllers.support.ViewConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class ViewResolution {
    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType(MediaType.TEXT_HTML_VALUE);
        viewResolver.setViewNames(ViewConstants.INDEX_VIEW);
        viewResolver.setExposeContextBeansAsAttributes(true);

        return viewResolver;
    }
}
