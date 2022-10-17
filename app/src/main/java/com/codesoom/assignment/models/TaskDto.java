package com.codesoom.assignment.models;

public class TaskDto {

    private Long id;
    private String title;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
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
