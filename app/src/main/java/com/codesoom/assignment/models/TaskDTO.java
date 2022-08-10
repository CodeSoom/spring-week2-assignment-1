package com.codesoom.assignment.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDTO {
    private final String title;

    @JsonCreator
    public TaskDTO(@JsonProperty("title") String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskDTO{");
        sb.append("title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
