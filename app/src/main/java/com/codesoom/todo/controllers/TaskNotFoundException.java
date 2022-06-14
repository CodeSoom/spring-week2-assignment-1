package com.codesoom.todo.controllers;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Could not find task " + taskId);
    }
}
