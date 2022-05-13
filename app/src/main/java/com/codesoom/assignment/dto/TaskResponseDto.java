package com.codesoom.assignment.dto;

public class TaskResponseDto {
    private Long id;
    private String title;

    public TaskResponseDto(Long id, String title) {
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
