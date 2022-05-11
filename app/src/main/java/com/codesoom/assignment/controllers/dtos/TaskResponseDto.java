package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.Task;

public class TaskResponseDto {
    private Long id;
    private String title;

    public TaskResponseDto() {
    }

    public TaskResponseDto(Task task) {
        this.id = task.id();
        this.title = task.title();
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
