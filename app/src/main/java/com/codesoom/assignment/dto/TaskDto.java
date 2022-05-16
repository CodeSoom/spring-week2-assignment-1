package com.codesoom.assignment.dto;

public class TaskDto {
    private Long id;
    private String title;

    public TaskDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
