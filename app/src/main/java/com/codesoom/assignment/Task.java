package com.codesoom.assignment;


import com.codesoom.assignment.interfaces.DefaultTask;

public class Task implements DefaultTask, Comparable<Task> {
    private Long id;
    private String title;

    Task() {
    }

    public Task(String title) {
        this.title = title;
    }

    Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public void updateId(Long id) {
        this.id = id;
    }

    @Override
    public void updateTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return String.format("{ id = %s, title = %s }", id, title);
    }

    @Override
    public int compareTo(Task task) {
        if (id() == null || task.id() == null) {
            return 0;
        }
        return id().compareTo(task.id());
    }
}
