package com.codesoom.assignment.domain.dtos;

public class TaskDTO {
    private final String title;

    public TaskDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
