package com.tpg.smp.web.context;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SmpWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String WEBAPP_NAME = "smp";

    static final String WEBAPP_PREFIX = String.format("/%s", WEBAPP_NAME);

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/", WEBAPP_PREFIX};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SmpWebConfig.class};
    }
}
