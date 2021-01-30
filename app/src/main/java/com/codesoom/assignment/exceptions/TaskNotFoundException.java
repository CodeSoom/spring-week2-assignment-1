package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException{

    private final static String errorMessage = "Can't find task that same as input";

    public TaskNotFoundException() {
        super(errorMessage);
    }


}
