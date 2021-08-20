package com.codesoom.assignment.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.OptionalInt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.repositories.TaskRepository;

public final class IdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final Map<String, String> pathVariable = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        final Long id = Long.parseLong(pathVariable.get("id"));
        final OptionalInt optionalInt = TaskRepository.findTaskIndex(id);
        if (optionalInt.isEmpty()) {
            throw new TaskNotFoundException();
        }
        request.setAttribute("taskIndex", optionalInt.getAsInt());
        return true;
    }
}
