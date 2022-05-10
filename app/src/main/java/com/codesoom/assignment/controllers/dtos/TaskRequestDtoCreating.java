package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.Task;

public class TaskRequestDtoCreating {
    private String title;

    TaskRequestDtoCreating() {
    }

    public TaskRequestDtoCreating(Task task) {
        this.title = task.title();
    }

    public String title() {
        return title;
    }

    public Task toEntity() {
        return new Task(title);
    }
}
