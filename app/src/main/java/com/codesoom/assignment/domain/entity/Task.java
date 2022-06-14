package com.codesoom.assignment.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(columnDefinition = "VARCHAR(255)", name="", nullable = false)
    private final String task;

    public Task(String task) {
        this.id = null;
        this.task = task;
    }

    public Task() {
        this.id = null;
        this.task = null;
    }

    public String getTask() {
        return this.task;
    }
}
