package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.DefaultTask;

public class TaskRequestDto {
    private String title;

    private TaskRequestDto() {
    }

    public TaskRequestDto(final DefaultTask task) {
        this.title = task.title();
    }

    public String getTitle() {
        return title;
    }

    public DefaultTask toEntity() {
        return new DefaultTask(title);
    }
}
