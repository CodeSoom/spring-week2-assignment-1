package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(long id) {
        super(String.format("Task id: %d", id));
    }
}
