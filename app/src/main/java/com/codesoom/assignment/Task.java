package com.codesoom.assignment;


import com.codesoom.assignment.interfaces.DefaultTask;

import java.util.UUID;

public class Task implements DefaultTask, Comparable<Task> {
    private final Long id;
    private final String title;


    public Task(String title) {
        this.id = generatedId();
        this.title = title;
    }

    public Task(Long id, String title) {
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

    private Long generatedId() {
        return UUID.randomUUID().getMostSignificantBits() & Integer.MAX_VALUE;
    }
}
