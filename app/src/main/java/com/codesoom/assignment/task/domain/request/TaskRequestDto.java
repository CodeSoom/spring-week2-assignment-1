package com.codesoom.assignment.task.domain.request;

import com.codesoom.assignment.task.domain.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskRequestDto {
    private final Long id;
    private final String title;

    public Task toEntity() {
        return Task.builder()
                .id(id)
                .title(title)
                .build();
    }

    public Task toEntity(Long id) {
        return Task.builder()
                .id(id)
                .title(title)
                .build();
    }
}
