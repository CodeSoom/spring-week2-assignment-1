package com.codesoom.assignment.domain;

public class Task {
    private final Long id;
    private String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public void changeTitle(String title) {
        this.title = title;
    }
}
