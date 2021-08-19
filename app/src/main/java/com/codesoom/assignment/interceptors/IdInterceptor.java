package com.codesoom.assignment.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class IdInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }
}
