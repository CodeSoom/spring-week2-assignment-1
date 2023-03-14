package com.codesoom.assignment.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(String msg) {
        super(msg);
    }
}
