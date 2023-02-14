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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("{ id = %s, title = %s }", id, title);
    }
}