package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TaskException extends ResponseStatusException {

    public TaskException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
