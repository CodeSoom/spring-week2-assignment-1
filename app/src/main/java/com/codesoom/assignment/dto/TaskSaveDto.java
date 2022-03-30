package com.codesoom.assignment.dto;

/**
 * Task 생성에 필요한 데이터를 정의합니다.
 */
public class TaskSaveDto {

    private String title;

    public TaskSaveDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
