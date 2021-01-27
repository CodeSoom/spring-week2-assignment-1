package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException{

    private final static String defaultMessage = "Not found task";

    public TaskNotFoundException() {
        super(defaultMessage);
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
