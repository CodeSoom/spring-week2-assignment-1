package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

public class TaskDto {

    private Long id;

    private String title;

    private TaskDto() {
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
