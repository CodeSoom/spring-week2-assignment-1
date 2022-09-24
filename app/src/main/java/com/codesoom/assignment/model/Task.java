package com.codesoom.assignment.model;

import org.apache.logging.log4j.util.Strings;

public class Task {

    private long id;

    private String title;

    public Task(long id, String title) {
        this.id = id;
        this.title = initTitle(title);
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
        this.title = initTitle(title);
    }

    public String initTitle(String title) {
        return Strings.isBlank(title) ? "No Title" : title;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title=" + title +
                '}';
    }
}
