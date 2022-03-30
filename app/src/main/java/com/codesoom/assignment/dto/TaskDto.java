package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

/**
 * {@link Task} 생성, 수정시 공통으로 필요한 데이터를 정의합니다.
 */
public class TaskDto {

    private String title;

    protected TaskDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
