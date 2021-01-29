package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException{

    private final static String defaultMessage = "task가 존재하지 않습니다.";

    public TaskNotFoundException() {
        super(defaultMessage);
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
