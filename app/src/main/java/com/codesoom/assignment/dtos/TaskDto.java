package com.codesoom.assignment.dtos;

public class TaskDto {
    String title;

    public TaskDto() {
    }

    public TaskDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
