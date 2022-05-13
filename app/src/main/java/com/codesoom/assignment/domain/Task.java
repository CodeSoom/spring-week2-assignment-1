package com.codesoom.assignment.domain;

import com.codesoom.assignment.exception.TaskHasNotInvalidTitleException;

import java.util.Objects;

public class Task implements Comparable<Task>{

    private final Long id;
    private String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void changeTitle(Task task) {
        this.title = task.title;
    }

    public boolean checkMyId(Long id) {
        return Long.compare(this.id, id) == 0 ? true : false;
    }

    public static Task createNewTaskWithTask(Long id, Task task) {
        return new Task(id, task.title);
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

    @Override
    public int compareTo(Task task) {
        return Long.compare(this.id, task.id);
    }

    /**
     * title이 존재하는지 여부를 반환함
     * @return
     */
    private boolean hasNotTitle() {
        return this.title.isEmpty();
    }

    /**
     * title이 현재 값을 갖고 있는지 확인
     * @return 존재하면 객체 그대로 반환, 존재하지 않으면 예외
     */
    public Task hasValidTitle() {
        if (hasNotTitle()) {
            throw new TaskHasNotInvalidTitleException();
        }

        return this;
    }
}
