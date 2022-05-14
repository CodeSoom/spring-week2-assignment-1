package com.codesoom.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
    public static final String NOT_EXIST = "Task(%d) does not exist.";

    public TaskNotFoundException() {
    }

    public static Supplier<TaskNotFoundException> of(Long id) {
        return () -> new TaskNotFoundException(id);
    }

    public TaskNotFoundException(Long id) {
        super(String.format(NOT_EXIST, id));
    }
}
