package com.codesoom.assignment.dto;

import com.codesoom.assignment.entity.Task;

import java.util.Objects;

public class TaskDto {
    private Long id;
    private String title;

    public TaskDto() {
    }

    public TaskDto(Task task) {
        this.id=task.getId();
        this.title= task.getTitle();
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
        TaskDto taskDto = (TaskDto) o;
        return Objects.equals(getId(), taskDto.getId()) &&
                Objects.equals(getTitle(), taskDto.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}
