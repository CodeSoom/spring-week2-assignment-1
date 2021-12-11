package com.codesoom.assignment.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(Throwable cause) {
        super(cause);
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

