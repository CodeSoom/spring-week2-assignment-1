package com.codesoom.assignment.domain;

import org.springframework.util.StringUtils;

import java.util.Objects;

public class Task implements Comparable<Task>{

    private final int EQUAL = 0;
    private final int NOT_EQUAL = 1;
    private Long id;
    private String title;

    public Task() {}

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        int result = Long.compare(this.id, task.id);

        if (result == 0) {
            result = (this.id.equals(task.title)) ? EQUAL : NOT_EQUAL;
        }
        return result;
    }
}
