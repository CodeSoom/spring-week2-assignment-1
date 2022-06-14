package com.codesoom.assignment.domain.entity;

public class Task {
    private Long id;

    private String task;

    public Task(String task) {
        this.id = null;
        this.task = task;
    }

    public String getTask() {
        return task;
    }
}
