package com.codesoom.assignment.model;

import java.util.Objects;

public class Task {
    private Long id;
    private String title;

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
    public String toString() {
        return String.format("<Task: %s, id: %s title: %s>", this.id, this.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }

        Task task = (Task) obj;
        return this.id == task.id &&
                Objects.equals(title, task.title);
    }
}
