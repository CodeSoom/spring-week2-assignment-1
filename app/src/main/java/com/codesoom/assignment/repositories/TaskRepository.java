package com.codesoom.assignment.repositories;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.dtos.TaskDto;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepository {
    private Map<Long, Task> taskMap = new ConcurrentHashMap<>();
    private long sequence = 0L;

    public long incrementSequence() {
        return ++sequence;
    }

    public Task save(TaskDto taskDto) {
        Task task = new Task(incrementSequence(), taskDto.getTitle());
        taskMap.put(task.getId(), task);
        return task;
    }
}
