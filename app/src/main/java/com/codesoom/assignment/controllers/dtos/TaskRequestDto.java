package com.codesoom.assignment.controllers.dtos;

import com.codesoom.assignment.DefaultTask;

import java.beans.ConstructorProperties;

public class TaskRequestDto {
    private final String title;

    @ConstructorProperties({"title"})
    private TaskRequestDto(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public DefaultTask toEntity() {
        return new DefaultTask(title);
    }
}
