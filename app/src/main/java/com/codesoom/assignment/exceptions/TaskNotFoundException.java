package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException{

    private final static String defaultMessage = "Can't find task that same as input";

    public TaskNotFoundException() {
        super(defaultMessage);
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
