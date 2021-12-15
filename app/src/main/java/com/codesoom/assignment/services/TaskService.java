package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> getTasks() {
        return tasks;
    }

    public Optional<Task> getTask(Long id) {
        Optional<Task> entity = tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
        return entity;
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task updateTask(Long id, Task source) {
        Optional<Task> entity = getTask(id);
        Task task = entity.get();
        task.setTitle(source.getTitle());
        return task;
    }

    public void deleteTask(Long id) {
        Optional<Task> entity = getTask(id);
        Task task = entity.get();
        tasks.remove(task);
    }

    private synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
