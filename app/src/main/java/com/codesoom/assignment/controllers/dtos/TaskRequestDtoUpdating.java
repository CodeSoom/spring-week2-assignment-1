package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.Task;

public class TaskRequestDtoUpdating {
    private Long id;
    private String title;

    TaskRequestDtoUpdating() {
    }

    public TaskRequestDtoUpdating(Long id, Task task) {
        this.id = id;
        this.title = task.title();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Task toEntity() {
        return new Task(title);
    }
}
