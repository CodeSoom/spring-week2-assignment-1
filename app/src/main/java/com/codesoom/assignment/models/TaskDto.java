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
        setId(task.getId());
        setTitle(task.getTitle());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
