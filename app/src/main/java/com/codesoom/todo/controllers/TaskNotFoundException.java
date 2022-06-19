package com.codesoom.todo.controllers;

import com.codesoom.todo.domain.Task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Could not find task " + taskId);
    }

    public TaskNotFoundException(Task task) {
        super("Could not find task " + task.getId());
    }
}
