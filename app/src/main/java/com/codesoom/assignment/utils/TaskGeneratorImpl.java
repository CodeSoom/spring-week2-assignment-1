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
    public Task generateNewTask(TaskDto dto) {
        dto.setId(idGenerator.allocateId());
        return dto.createNewTask();
    }

    @Override
    public Task changeTitle(Task originalTask, String title) {
        return originalTask.changeTitle(title);
    }
}
