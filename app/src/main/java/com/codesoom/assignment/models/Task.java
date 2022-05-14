package com.codesoom.assignment.models;

import com.codesoom.assignment.exceptions.BadRequestException;

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

    public void checkTitle(String title) {
        if(title.isBlank()) {
            throw new BadRequestException();
        }
    }
}
