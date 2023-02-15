package com.codesoom.assignment.domain;

public class Task {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void changeId(Long id) {
        this.id = id;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("{ id = %s, title = %s }", id, title);
    }
}