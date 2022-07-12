package com.codesoom.assignment.domain;

import java.util.Objects;

/**
 * 해야 할 일이라는 의미의 Task 클래스입니다.
 */
public class Task {
    private final Long id;
    private String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public boolean isSame(Long id) {
        return this.id.equals(id);
    }

    /**
     * 입력 받은 title로 현재 title을 바꾸고 바뀐 Task를 리턴한다.
     *
     * @param title 입력 받은 문자열 title
     * @return 바뀐 Task 리턴
     */
    public Task changeTitle(String title) {
        this.title = title;
        return this;
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
