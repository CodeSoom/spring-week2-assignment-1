package com.codesoom.assignment.models;

import javax.validation.constraints.NotEmpty;

public class TaskDto {

    @NotEmpty
    private String title;

    public TaskDto() {
    }

    public TaskDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("{ title = %s }", title);
    }

}
