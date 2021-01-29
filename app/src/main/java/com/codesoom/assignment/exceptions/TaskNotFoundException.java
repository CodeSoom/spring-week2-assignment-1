package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("task가 존재하지 않습니다.");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
