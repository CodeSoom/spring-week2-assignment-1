package com.codesoom.assignment.domain.dtos;

public class TaskDTO {
    private final String task;

    public TaskDTO(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }
}
