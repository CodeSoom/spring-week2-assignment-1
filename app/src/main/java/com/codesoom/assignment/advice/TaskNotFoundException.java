package com.codesoom.assignment.advice;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String msg) {
        super(msg);
    }
}
