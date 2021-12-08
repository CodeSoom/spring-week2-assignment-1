package com.codesoom.assignment.models;

import java.util.Optional;

public class Task {
    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public Optional<Task> setId(Long id) {
        this.id = id;
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        this.id = id;
        this.title = title;

        return String.format("Task-> id: %s, title: %s", id, title);
    }

}
