package com.codesoom.assignment;


import com.codesoom.assignment.interfaces.DefaultTask;

public class Task implements DefaultTask, Comparable<Task> {
    private Long id;
    private final String title;


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
    public String toString() {
        return String.format("{ id = %s, title = %s }", id, title);
    }

    @Override
    public int compareTo(Task task) {
        return id().compareTo(task.id());
    }
}
