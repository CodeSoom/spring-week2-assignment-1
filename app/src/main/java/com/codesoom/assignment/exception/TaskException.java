package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TaskException extends ResponseStatusException {

    // TODO Task 객체의 getter/setter 리펙토링 후, ControllerAdvice 적용해볼 것
    public TaskException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
