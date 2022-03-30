package com.codesoom.assignment.dto;

import com.codesoom.assignment.models.Task;

/**
 * 생성, 수정할 때 필요한 데이터 교환을 하기 위해 사용한다.
 */
public class TaskDto {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * TaskDto를 Task로 변경한다.
     * @return Task
     */
    public Task toModel() {
        return new Task.TaskBuilder()
                .title(this.title)
                .build();
    }
}
