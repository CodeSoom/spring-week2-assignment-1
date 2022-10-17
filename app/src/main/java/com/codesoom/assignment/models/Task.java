package com.codesoom.assignment.models;

public class Task {

    private final Long id;
    private final String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task(TaskDto dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
