package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

/**
 * {@link Task} 데이터 전송에 필요한 데이터를 정의합니다.
 */
public class TaskViewDto {

    private final Task task;

    private TaskViewDto(Task task) {
        this.task = task;
    }

    /**
     * {@link Task} 객체를 인자로 받아 {@link TaskDto} 를 리턴합니다.
     *
     * @param task {@link Task}
     * @return {@link TaskDto}
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
