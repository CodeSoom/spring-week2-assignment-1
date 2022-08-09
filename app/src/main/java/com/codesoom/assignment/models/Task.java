package com.codesoom.assignment.models;

import com.codesoom.assignment.dto.TaskDto;
import java.util.Objects;

public class Task {

    private final Long id;

    private final String title;

    public Task(Long id, String title) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
    }

    public Task from(TaskDto dto) {
        return new Task(dto.getId(), dto.getTitle());
    }
}
