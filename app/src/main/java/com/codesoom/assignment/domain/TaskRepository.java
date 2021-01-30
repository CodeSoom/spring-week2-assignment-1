package com.codesoom.assignment.domain;

import com.codesoom.assignment.optional.Empty;

import java.util.*;

public class TaskRepository {
    private final Map<Long, Task> taskMap = new HashMap<>();
    private Long lastId = 1L;

    public Optional<Empty> save(Task task) {
        taskMap.put(task.getId(), task);
        return Optional.ofNullable(Empty.empty);
    }

    public Long nextId() {
        return lastId++;
    }

    public Optional<Task> getById(Long taskId) {
        return Optional.ofNullable(taskMap.get(taskId));
    }

    public List<Task> getAllTask() {
        return new ArrayList<>(taskMap.values());
    }

    public Optional<Empty> delete(Task task) {
        taskMap.remove(task.getId());
        return Optional.ofNullable(Empty.empty);
    }
}
