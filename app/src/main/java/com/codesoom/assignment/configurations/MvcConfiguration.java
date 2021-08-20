package com.codesoom.assignment.configurations;

import com.codesoom.assignment.interceptors.IdInterceptor;
import com.codesoom.assignment.utils.UrlPath;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new IdInterceptor()).addPathPatterns(UrlPath.TASKS + UrlPath.ID);
    }
}
