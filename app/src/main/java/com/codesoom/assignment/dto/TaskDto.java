package com.codesoom.assignment.dto;

public class TaskDto {

    private Long id;

    private String title;

    protected TaskDto(TaskDtoBuilder taskDtoBuilder) {
        this.id = taskDtoBuilder.id != null ? taskDtoBuilder.id : 0L;
        this.title = taskDtoBuilder.title != null ? taskDtoBuilder.title : "";
    }

    public static TaskDto from(Long id, String title) {
        return new TaskDto(new TaskDtoBuilder()
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
