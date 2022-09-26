package com.codesoom.assignment.models;

public class Task {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Task newTitle(String title) {
        return new Task(this.getId(), title);
    }

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task() {
    }

    public String toString() {
        return "Task-Title: " + title;
    }

}
