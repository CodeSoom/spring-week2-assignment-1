package com.codesoom.assignment;


import java.util.UUID;

public class DefaultTask implements com.codesoom.assignment.interfaces.Task, Comparable<DefaultTask> {
    private final Long id;
    private final String title;


    public DefaultTask(final String title) {
        this.id = generatedId();
        this.title = title;
    }

    public DefaultTask(final Long id, final String title) {
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
    public int compareTo(final DefaultTask task) {
        return id().compareTo(task.id());
    }

    private Long generatedId() {
        return UUID.randomUUID().getMostSignificantBits() & Integer.MAX_VALUE;
    }
}
