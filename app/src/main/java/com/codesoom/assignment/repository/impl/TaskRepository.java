package com.codesoom.assignment.repository.impl;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.ITaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public Task findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Task save(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public void delete(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
