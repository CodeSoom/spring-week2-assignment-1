package com.codesoom.assignment.controllers;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task not Found : " + id);
    }
}
