package com.codesoom.assignment.models;

public class Task {
    private long id;
    private String title;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return "Task - title : " + title;
    }


}
