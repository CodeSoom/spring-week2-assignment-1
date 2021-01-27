package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException{

    private final static String defaultMessage = "Not found Task";

    public TaskNotFoundException() {
        super(defaultMessage);
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
