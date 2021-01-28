package com.codesoom.assignment.models;

public class Task {

    private Long id;

    private String title;

    public Task() {
    }

    public Task(String title) {
        this.title = title;
    }

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void update(Task task) {
        this.title = task.getTitle();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("{ id = %s, title = %s }", id, title);
    }

}
