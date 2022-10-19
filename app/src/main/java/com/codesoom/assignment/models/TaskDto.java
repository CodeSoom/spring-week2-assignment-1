package com.codesoom.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDto {

    @JsonProperty private Long id;
    @JsonProperty private String title;
    @JsonIgnore private Task task;

    public TaskDto() {
    }

    public TaskDto(Task task) {
        this.task = task;
        if (task == null) {
            return;
        }
        setId(task.getId()).setTitle(task.getTitle());
    }

    public Long getId() {
        return id;
    }

    public TaskDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TaskDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Task createNewTask() {
        this.task = new Task(id, title);
        return this.task;
    }
}
