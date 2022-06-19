package com.codesoom.assignment.domain.entity;

import com.codesoom.assignment.domain.dtos.TaskDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)", name="", nullable = false)
    private String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task(String title) {
        this.title = title;
    }

    public Task() {}

    public static Task from(TaskDTO dto) {
        return new Task(dto.getTitle());
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
