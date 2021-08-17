package com.codesoom.assignment.model;

public final class Task {
    private final Long id;
    private String title;

    public Task(final Long id, final String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
