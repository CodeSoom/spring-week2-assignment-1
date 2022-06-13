package com.codesoom.assignment.models;

import javax.validation.constraints.NotEmpty;

public class TaskRequestForm {

    @NotEmpty
    private String title;

    public TaskRequestForm(String title) {
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
