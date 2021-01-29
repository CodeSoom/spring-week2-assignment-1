package com.codesoom.assignment.domain;

import java.util.Objects;

/**
 * 할 일을 나타내는 클래스.
 *
 * @author etff
 * @version 1.0.0 21/01/30
 * @see com.codesoom.assignment.domain.Task
 */
public class Task {
    private Long id;

    private String title;

    public Task() {
    }

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * 할 일의 아이디를 돌려준다.
     * @return 할 일의 아이디
     */
    public Long getId() {
        return id;
    }

    /**
     * 할 일의 아이디 값을 지정한다.
     * @param id 할일의 해당하는 아이디
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 어떤 할 일인지 돌려준다.
     * @return 해야할 할 일 명칭
     */
    public String getTitle() {
        return title;
    }

    /**
     * 해야할 할 일을 지정한다.
     * @param title 해야할 할 일 명칭
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("id = %s, title = %s", id, title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
