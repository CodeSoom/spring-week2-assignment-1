package com.codesoom.assignment.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(Long id) {
        super("Task not found : " + id);
    }
}
