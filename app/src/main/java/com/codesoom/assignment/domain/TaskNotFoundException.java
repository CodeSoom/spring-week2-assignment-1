package com.codesoom.assignment.domain;

public class TaskNotFoundException extends RuntimeException {
    private final String defaultMessage = "Not found task id";
    private final String message;

    public TaskNotFoundException(long id) {
        super();
        this.message = String.format("%s: %d", defaultMessage, id);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
