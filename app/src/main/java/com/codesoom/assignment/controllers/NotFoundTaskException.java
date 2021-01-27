package com.codesoom.assignment.controllers;

public class NotFoundTaskException extends Exception {
    private final Long taskId;

    public NotFoundTaskException(Long id) {
        this.taskId = id;
    }

    public String getMessage() {
        return "NotFoundTask: task id=" + this.taskId;
    }
}
