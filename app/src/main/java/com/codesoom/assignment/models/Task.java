package com.codesoom.assignment.models;

public class Task {

    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, title=%s}", id, title);
    }

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
