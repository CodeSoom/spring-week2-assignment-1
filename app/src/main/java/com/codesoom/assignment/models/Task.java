package com.codesoom.assignment.models;

public class Task {

    private String title;
    private Long id = 0L;

    // For serialize or deserialize by jackson library
    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%s, title=%s", id, title);
    }
}
