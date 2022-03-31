package com.codesoom.assignment.dto;

import javax.validation.constraints.NotBlank;

public class TaskDto {
    private Long id;

    @NotBlank(message = "title이 비어있습니다.")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
