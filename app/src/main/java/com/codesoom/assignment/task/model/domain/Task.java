package com.codesoom.assignment.task.model.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.codesoom.assignment.task.util.IdGenerator.getIncrementId;

@EqualsAndHashCode(of = {"id"})
@ToString
public class Task {

    private final long id;
    private final String title;

    private Task(final long id, final String title) {
        this.id = id;
        this.title = title;
    }

    public static Task from(final String title) {
        return new Task(getIncrementId(), title);
    }

    public static Task of(final long id, final String title) {
        return new Task(id, title);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
