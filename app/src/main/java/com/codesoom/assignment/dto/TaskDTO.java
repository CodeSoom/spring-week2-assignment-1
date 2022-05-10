package com.codesoom.assignment.dto;

import com.codesoom.assignment.model.Task;

public class TaskDTO {

    private Long id;
    private String title;

    public TaskDTO() {

    }
    public TaskDTO(Task task) {
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
