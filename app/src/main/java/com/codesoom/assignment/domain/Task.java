package com.codesoom.assignment.domain;

import java.util.Objects;

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

    public boolean isSame(Long id) {
        return this.id.equals(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
