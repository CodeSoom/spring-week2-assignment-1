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
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)", name="", nullable = false)
    private String title;

    public Task(String title) {
        this.id = null;
        this.title = title;
    }

    public Task() {}

    public String getTitle() {
        return this.title;
    }
}
