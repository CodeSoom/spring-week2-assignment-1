package com.codesoom.assignment.dto;


public class TaskDtoBuilder {

    protected Long id;

    protected String title;

    protected TaskDtoBuilder() {
        this.id = null;
        this.title = null;
    }

    public TaskDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TaskDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskDto build() {
        return new TaskDto(this);
    }
}
