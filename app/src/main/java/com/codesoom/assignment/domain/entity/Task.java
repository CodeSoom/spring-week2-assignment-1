package com.codesoom.assignment.domain.entity;

public class Task {
    private final Long id;

    private final String task;

    public Task(String task) {
        this.id = null;
        this.task = task;
    }

    public String getTask() {
        return task;
    }
}
