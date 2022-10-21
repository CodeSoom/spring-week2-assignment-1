package com.codesoom.assignment.task.controller.dto.response;

import com.codesoom.assignment.task.domain.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskResponseDto {
    private final Long id;
    private final String title;

    public static TaskResponseDto from(Task task) {
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .build();
    }
}
