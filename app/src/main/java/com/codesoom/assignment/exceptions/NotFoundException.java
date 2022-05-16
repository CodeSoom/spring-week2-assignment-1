package com.codesoom.assignment.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Task not found: " + id);
    }
}
