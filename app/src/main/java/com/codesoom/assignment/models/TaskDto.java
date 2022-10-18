package com.codesoom.assignment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDto {

    @JsonProperty private Long id;
    @JsonProperty private String title;
    private Task task;

    public TaskDto() {
    }

    public TaskDto(Task task) {
        this.task = task;
        if (task == null) {
            return;
        }
        setId(task.getId());
        setTitle(task.getTitle());
    }

    public void setId(Long id) {
        this.id = id;
        if (title != null) {
            task = new Task(id, title);
        }
    }

    public void setTitle(String title) {
        this.title = title;
        if (id != null) {
            task = new Task(id, title);
        }
    }

    public Task getTask() {
        return task;
    }
}
