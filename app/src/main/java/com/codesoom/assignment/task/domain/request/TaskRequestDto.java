package com.codesoom.assignment.task.domain.request;

import com.codesoom.assignment.task.domain.Task;
import lombok.Getter;

@Getter
public class TaskRequestDto {
    private String title;

    public Task toEntity(Long id) {
        return Task.builder()
                .id(id)
                .title(title)
                .build();
    }
}
