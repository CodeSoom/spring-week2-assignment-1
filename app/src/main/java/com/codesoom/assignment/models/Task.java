package com.codesoom.assignment.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    private final Long id;
    private final String title;

    /**
     * Constructor.
     * Set all properties.
     *
     * @param id is task's id.
     * @param title is task's title.
     */
    @JsonCreator
    public Task(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title
    ) {
        this.id = id;
        this.title = title;
    }

    /**
     * Returns task's id.
     */
    @JsonGetter
    public Long id() {
        return id;
    }

    /**
     * Returns task's title.
     */
    @JsonGetter
    public String title() {
        return title;
    }
}
