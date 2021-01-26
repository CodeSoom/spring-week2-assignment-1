package com.codesoom.assignment.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    private final Long id;
    private final String title;

    @JsonCreator
    public Task(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title
    ) {
        this.id = id;
        this.title = title;
    }

    @JsonGetter
    public Long id() {
        return id;
    }

    @JsonGetter
    public String title() {
        return title;
    }
}
