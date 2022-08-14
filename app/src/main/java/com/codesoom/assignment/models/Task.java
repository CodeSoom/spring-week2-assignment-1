package com.codesoom.assignment.models;

import com.codesoom.assignment.dto.TaskDto;
import java.util.Objects;

public class Task {

    private final Long id;

    private final String title;

    private Task(Long id, String title) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
    }

    public static Task from(TaskDto dto) {
        return new Task(dto.getId(), dto.getTitle());
    }

    public Long getId() {
        return this.id;
    }
}
