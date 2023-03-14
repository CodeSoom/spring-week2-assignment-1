package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

public class TaskDto {
    Task task;

    Long id;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
