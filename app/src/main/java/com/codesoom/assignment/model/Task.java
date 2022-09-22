package com.codesoom.assignment.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Task {

    private long id;

    private String title;

    public Task(long id, String title) {
        this.id = id;
        this.title = isNullOrBlank(title) ? "No Title" : title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void initTitle(String title) {
        this.title = isNullOrBlank(title) ? "No Title" : title;
    }

    public boolean isNullOrBlank(String s) {
        return s == null || s.isBlank();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
