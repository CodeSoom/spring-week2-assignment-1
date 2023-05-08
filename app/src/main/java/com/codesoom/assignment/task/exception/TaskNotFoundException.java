package com.codesoom.assignment.task.exception;

public class TaskNotFoundException extends IllegalArgumentException {

    public TaskNotFoundException(final long id) {
        super(String.format("Task Not Found id : %d", id));
    }

}
