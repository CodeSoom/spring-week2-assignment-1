package com.codesoom.assignment.common.util;

import com.codesoom.assignment.domain.dtos.TaskDTO;
import com.codesoom.assignment.domain.entity.Task;

public class TaskMapper {
    public static Task toEntity(TaskDTO dto) {
        return new Task(dto.getTask());
    }

    public static TaskDTO toDTO(Task entity) {
        return new TaskDTO(entity.getTask());
    }
}
