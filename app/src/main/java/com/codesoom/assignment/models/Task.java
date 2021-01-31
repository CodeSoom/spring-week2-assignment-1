package com.codesoom.assignment.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    private final Integer id;
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
            @JsonProperty("id") Integer id,
            @JsonProperty("title") String title
    ) {
        this.id = id;
        this.title = title;
    }

    /**
     * Returns task's id.
     */
    @JsonGetter
    public Integer id() {
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
