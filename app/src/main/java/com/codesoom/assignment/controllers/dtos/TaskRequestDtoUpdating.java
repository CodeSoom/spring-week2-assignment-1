package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.Task;

public class TaskRequestDtoUpdating {
    private final Long id;
    private final String title;

    public TaskRequestDtoUpdating(Long id, Task task) {
        this.id = id;
        this.title = task.title();
    }

    public Long id() {
        return id;
    }

    public Task toEntity(){
        return new Task(title);
    }
}
