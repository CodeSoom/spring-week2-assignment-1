package com.codesoom.assignment.dto;

import com.codesoom.assignment.domain.Task;

/**
 * 할 일 생성에 필요한 데이터를 정의합니다.
 *
 * @see TaskDto
 */
public class TaskSaveDto {

    private final TaskDto taskDto;

    public TaskSaveDto() {
        this.taskDto = new TaskDto();
    }

    public String getTitle() {
        return taskDto.getTitle();
    }

    public void setTitle(final String title) {
        taskDto.setTitle(title);
    }

    /**
     * 할 일로 변환해 리턴합니다.
     */
    public Task toEntity() {
        return new Task(taskDto.getTitle());
    }
}
