package com.codesoom.assignment.models;

/**
 * 할 일.
 */
public class Task {
    private final Long id;
    private final String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%s, title=%s}", id, title);
    }
}
