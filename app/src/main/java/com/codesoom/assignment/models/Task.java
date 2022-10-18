package com.codesoom.assignment.models;

import java.time.LocalDateTime;

public class Task {

    private final Long id;
    private final String title;
    private final LocalDateTime regDate;
    private final LocalDateTime modDate;

    public Task(Long id, String title, LocalDateTime regDate) {
        this.id = id;
        this.title = title;
        this.regDate = regDate;
        this.modDate = regDate;
    }

    public Task(Long id, String title, LocalDateTime regDate, LocalDateTime modDate) {
        this.id = id;
        this.title = title;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public LocalDateTime getModDate() {
        return modDate;
    }
}
