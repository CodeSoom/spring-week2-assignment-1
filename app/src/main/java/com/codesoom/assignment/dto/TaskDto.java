package com.codesoom.assignment.dto;

public class TaskDto {

    private Long id;

    private String title;

    protected TaskDto(Builder builder) {
        this.id = builder.id != null ? builder.id : 0L;
        this.title = builder.title != null ? builder.title : "";
    }

    public static TaskDto from(Long id, String title) {
        return new TaskDto(new Builder()
                .withId(id)
                .withTitle(title));
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

}
