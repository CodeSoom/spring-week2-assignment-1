package com.codesoom.assignment.controllers;

public class TaskNotFoundException extends Exception {
    private final Long taskId;

    public TaskNotFoundException(Long id) {
        this.taskId = id;
    }

    public String getMessage() {
        return "TaskNotFoundException: task id=" + this.taskId;
    }
}
