package com.codesoom.assignment.models;

import java.time.LocalDateTime;

public class Task {

    private final Long id;
    private final String title;
    private final LocalDateTime regDate;
    private final LocalDateTime modDate;

    Task(Long id, String title) {
        this.id = id;
        this.title = title;
        this.regDate = LocalDateTime.now();
        this.modDate = this.regDate;
    }

    private Task(Long id, String title, LocalDateTime regDate, LocalDateTime modDate) {
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

    public Task changeTitle(String title) {
        return new Task(this.id, title, this.regDate, LocalDateTime.now());
    }
}
