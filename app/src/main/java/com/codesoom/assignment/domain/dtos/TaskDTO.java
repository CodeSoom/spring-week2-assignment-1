package com.codesoom.assignment.domain.dtos;

import com.codesoom.assignment.domain.entity.Task;

public class TaskDTO {
    private String title;

    public TaskDTO() {}

    public TaskDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static TaskDTO from(Task entity) {
        return new TaskDTO(entity.getTitle());
    }
}
