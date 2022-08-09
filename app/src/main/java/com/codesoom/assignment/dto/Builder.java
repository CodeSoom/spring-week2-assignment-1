package com.codesoom.assignment.dto;


public class Builder {

    protected Long id;

    protected String title;

    protected Builder() {
        this.id = null;
        this.title = null;
    }

    public Builder withId(Long id) {
        this.id = id;
        return this;
    }

    public Builder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskDto build() {
        return new TaskDto(this);
    }
}
