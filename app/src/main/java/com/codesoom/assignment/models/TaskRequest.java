package com.codesoom.assignment.models;

public class TaskRequest {
    private final String title;

    private static final String EMPTY_TITLE = "";

    public TaskRequest() {
        this(EMPTY_TITLE);
    }

    public TaskRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
