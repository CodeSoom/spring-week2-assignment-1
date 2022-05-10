package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.Task;

public class TaskRequestDto {
    private String title;

    TaskRequestDto() {
    }

    public TaskRequestDto(Task task) {
        this.title = task.title();
    }

    public String getTitle() {
        return title;
    }

    public Task toEntity() {
        return new Task(title);
    }
}
