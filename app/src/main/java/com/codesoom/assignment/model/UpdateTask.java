package com.codesoom.assignment.model;

public class UpdateTask {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "UpdateTask{" +
                "title='" + title + '\'' +
                '}';
    }
}
