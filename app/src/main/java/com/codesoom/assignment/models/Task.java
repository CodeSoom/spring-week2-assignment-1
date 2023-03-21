package com.codesoom.assignment.models;


public class Task {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("{ id = %s , title = %s }", id, title);
    }
}
