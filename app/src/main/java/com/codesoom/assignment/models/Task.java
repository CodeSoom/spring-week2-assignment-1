package com.codesoom.assignment.models;

public class Task {
    private Long id;

    private Title title;

    public Task(Long id, Title title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public void updateTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
