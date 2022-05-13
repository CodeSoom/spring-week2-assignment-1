package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.Task;

public class TaskResponseDto {
    private final Long id;
    private final String title;


    public TaskResponseDto(final Task task) {
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
