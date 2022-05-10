package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.Task;

public class TaskResponseDto {
    private String title;

    TaskResponseDto() {
    }

    public TaskResponseDto(Task task) {
        this.title = task.title();
    }

    public String getTitle() {
        return title;
    }

}
