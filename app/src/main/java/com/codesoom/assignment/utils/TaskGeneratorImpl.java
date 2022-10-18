package com.codesoom.assignment.utils;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskGeneratorImpl implements TaskGenerator {

    public final TaskIdGenerator idGenerator;

    public TaskGeneratorImpl(TaskIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Task generateNewTask(TaskDto dto) {
        return new Task(idGenerator.allocateId(), dto.getTitle(), LocalDateTime.now());
    }

    @Override
    public Task changeTitle(Task originalTask, String title) {
        return new Task(originalTask.getId(), title, originalTask.getRegDate(), originalTask.getModDate());
    }
}
