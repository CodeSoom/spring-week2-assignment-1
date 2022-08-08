package com.codesoom.assignment.domain;

import java.util.Objects;

/**
 * 사용자의 작업을 의미합니다.
 * 사용자에게 작업 번호와 제목으로 해야 할 일을 명시해 줄 책임을 가지고 있습니다.
 */
public class Task {
    private final Long id;
    private String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Jackson Binding을 위한 메서드입니다.
     *
     * @return 요소의 id
     */
    public Long getId() {
        return id;
    }

    /**
     * Jackson Binding을 위한 메서드입니다.
     *
     * @return 요소의 title
     */
    public String getTitle() {
        return title;
    }

    public boolean isSame(Long id) {
        return this.id.equals(id);
    }

    /**
     * 입력 받은 title로 현재 title을 바꾸고 바뀐 작업을 리턴한다.
     *
     * @param title 입력 받은 문자열 title
     * @return 바뀐 작업 리턴
     */
    public Task changeTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        return Objects.equals(id, task.id) && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
