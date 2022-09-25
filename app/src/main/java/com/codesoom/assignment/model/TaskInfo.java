package com.codesoom.assignment.model;

public class TaskInfo {

    private final long id;

    private final String title;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskInfo(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title=" + title +
                '}';
    }
}

