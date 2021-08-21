package com.codesoom.assignment.exception;

public class TaskNotFoundException extends RuntimeException{

    private String message;
    private String BASIC_MESSAGE = "task를 찾지 못했습니다";

    public TaskNotFoundException(String message) {
        super(message);
        this.message = String.format("%s %s", message, BASIC_MESSAGE);
    }
}
