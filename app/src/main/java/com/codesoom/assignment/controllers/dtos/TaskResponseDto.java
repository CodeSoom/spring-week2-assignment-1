package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.Task;

public class TaskResponseDto {
    private final String title;

    public TaskResponseDto(Task task) {
        this.title = task.title();
    }

    public String title() {
        return title;
    }

}
