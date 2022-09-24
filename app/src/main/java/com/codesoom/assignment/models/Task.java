package com.codesoom.assignment.models;

public class Task {
    private final Long id;
    private final String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Task 값 복사를 한다.
     */
    public static Task from(Task task) {
        return new Task(task.getId(), task.getTitle());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
