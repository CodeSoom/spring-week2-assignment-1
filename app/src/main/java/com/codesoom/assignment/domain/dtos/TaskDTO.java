package com.codesoom.assignment.domain.dtos;

public class TaskDTO {
    private String title;

    public TaskDTO() {}

    public TaskDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
