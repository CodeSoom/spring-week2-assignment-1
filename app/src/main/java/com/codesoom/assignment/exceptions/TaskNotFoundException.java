package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("Incorrect id");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
