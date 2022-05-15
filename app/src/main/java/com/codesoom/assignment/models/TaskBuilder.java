package com.codesoom.assignment.models;

public class TaskBuilder {
    private Long id;
    private String title;

    public TaskBuilder(){}

    public Task build() {
        return new Task(id, title);
    }

    public TaskBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TaskBuilder title(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

}
