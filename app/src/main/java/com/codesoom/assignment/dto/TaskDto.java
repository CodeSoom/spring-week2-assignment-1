package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

/**
 * Task 데이터 전송에 필요한 데이터를 정의합니다.
 */
public class TaskDto {

    private Long id;

    private String title;

    private TaskDto() {
    }

    /**
     * Task 객체를 인자로 받아 TaskDto 를 리턴합니다.
     * @param task Task 객체
     * @return TaskDto 객체
     */
    public static TaskDto from(final Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.id = task.getId();
        taskDto.title = task.getTitle();
        return taskDto;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
