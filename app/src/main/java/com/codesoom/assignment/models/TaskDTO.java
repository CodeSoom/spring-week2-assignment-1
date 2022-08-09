package com.codesoom.assignment.models;

public class TaskDTO {
    private String title;

    public TaskDTO() {
    }

    public TaskDTO(String title) {
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
