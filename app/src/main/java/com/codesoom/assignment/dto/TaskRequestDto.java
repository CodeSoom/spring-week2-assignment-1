package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

public class TaskRequestDto {
    private Long id;
    private String title;

    public TaskRequestDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task toTask() {
        return new Task(id, title);
    }
}
