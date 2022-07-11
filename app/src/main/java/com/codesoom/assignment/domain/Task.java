package com.codesoom.assignment.domain;

/**
 * 해야 할 일이라는 의미의 Task 클래스입니다.
 */
public class Task {
    private final Long id;
    private final String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
