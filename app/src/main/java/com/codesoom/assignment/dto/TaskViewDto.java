package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

/**
 * 할 일 데이터 전송에 필요한 데이터를 정의합니다.
 */
public class TaskViewDto {

    private final Task task;

    private TaskViewDto(Task task) {
        this.task = task;
    }

    /**
     * 할 일을 받아 전송에 필요한 데이터를 리턴합니다.
     *
     * @param task 할 일
     */
    public static TaskViewDto from(final Task task) {
        return new TaskViewDto(task);
    }

    public Long getId() {
        return this.task.getId();
    }

    public String getTitle() {
        return this.task.getTitle();
    }
}
