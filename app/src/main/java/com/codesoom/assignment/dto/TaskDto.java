package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

public class TaskDto {

    private Long id;

    private String title;

    private TaskDto() {
    }

    public static TaskDto from(final Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.id = task.getId();
        taskDto.title = task.getTitle();
        return taskDto;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
