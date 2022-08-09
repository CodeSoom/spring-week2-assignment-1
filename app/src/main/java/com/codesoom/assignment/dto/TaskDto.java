package com.codesoom.assignment.dto;

public class TaskDto {

    private Long id;

    private String title;

    public TaskDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
