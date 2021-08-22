package com.codesoom.assignment.model;

public class TaskResponse {
    private final Long id;
    private final String title;

    public TaskResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
