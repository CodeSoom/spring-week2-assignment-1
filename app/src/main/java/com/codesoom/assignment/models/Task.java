package com.codesoom.assignment.models;

/**
 * 할 일 객체입니다.
 */
public class Task {

    /**
     * 식별자 Id
     */
    private Long id;

    /**
     * 할 일 제목
     */
    private String title;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, title=%s}", id, title);
    }

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
