package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

/**
 * Task 데이터 전송에 필요한 데이터를 정의합니다.
 */
public class TaskDto {

    private final Task task;

    private TaskDto(Task task) {
        this.task = task;
    }

    /**
     * Task 객체를 인자로 받아 TaskDto 를 리턴합니다.
     *
     * @param task Task 객체
     * @return TaskDto 객체
     */
    public static TaskDto from(final Task task) {
        return new TaskDto(task);
    }

    public Long getId() {
        return this.task.getId();
    }

    public String getTitle() {
        return this.task.getTitle();
    }
}
