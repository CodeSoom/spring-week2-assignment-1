package com.codesoom.assignment.models;

public class Task {

    private long id;
    private String title;

    @Override
    public String toString() {
        return String.format("{id=%d, title=%s}, id, title");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
