package com.codesoom.assignment.configurations;

import com.codesoom.assignment.interceptors.IdInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    /**
     * "/tasks/{id}"로의 http 요청 시
     * IdInterceptor의 preHandle 메서드가 TaskController보다 먼저 불리도록 설정
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new IdInterceptor()).addPathPatterns("/tasks/{id}");
    }
}
