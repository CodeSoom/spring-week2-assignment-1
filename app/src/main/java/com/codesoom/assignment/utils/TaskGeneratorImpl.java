package com.codesoom.assignment.utils;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskGeneratorImpl implements TaskGenerator {

    public final TaskIdGenerator idGenerator;

    public TaskGeneratorImpl(TaskIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Task generateTask(TaskDto dto) {
        dto.setId(idGenerator.allocateId());
        return dto.getTask();
    }
}
