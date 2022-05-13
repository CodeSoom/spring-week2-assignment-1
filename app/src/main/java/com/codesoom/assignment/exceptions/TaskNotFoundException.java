package com.codesoom.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
    public static final String NOT_EXIST = "Task(%d) does not exist.";

    public TaskNotFoundException() {
    }

    public TaskNotFoundException(Long id) {
        super(String.format(NOT_EXIST, id));
    }
}
