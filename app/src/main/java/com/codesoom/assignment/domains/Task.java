package com.codesoom.assignment.domains;

import java.util.Objects;

public class Task {

    private Long id;

    private String title;

    public Task() {}

    public Task(String title) {
        this.title = title;
    }

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void nextId(Long lastIdx) {
        this.id = lastIdx + 1;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public boolean equalTaskId(Long taskId) {
        return Objects.equals(id, taskId);
    }

    public boolean hasValidContent() {
        return title != null && !title.isEmpty();
    }

    public boolean equalTaskTitle(String title) {
        return hasValidContent() && Objects.equals(this.title, title);
    }

    public Task editTaskTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) && Objects.equals(getTitle(), task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}
