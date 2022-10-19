package com.codesoom.assignment.task.domain.request;

import com.codesoom.assignment.task.domain.Task;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TaskRequestDto {
    @NotBlank
    private String title;

    public Task toEntity(Long id) {
        return Task.builder()
                .id(id)
                .title(title)
                .build();
    }
}
