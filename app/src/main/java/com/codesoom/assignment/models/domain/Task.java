package com.codesoom.assignment.models.domain;

/**
 * 할 일 객체
 */
public class Task {
    private Long id;
    private String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

}
