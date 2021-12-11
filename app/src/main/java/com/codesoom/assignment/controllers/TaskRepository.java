package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskRepository {

    private static Map<Long, Task> tasks = new LinkedHashMap<>();
    private static Long taskId = 0L;

    public ArrayList<Task> findList() {
        return new ArrayList<>(tasks.values());
    }

    public Task add(Task task) {
        task.setId(generateId());
        tasks.put(taskId, task);
        return task;
    }

    public Optional<Task> findDetail(Long id) {
        Task task = tasks.get(id);
        return Optional.ofNullable(task);
    }

    public Optional<Task> update(Long id, String title) {
        Optional<Task> find = findDetail(id);

        find.ifPresent(task -> task.setTitle(title));

        return find;
    }

    public void delete(Long id) {
        tasks.remove(id);
    }

    private static synchronized Long generateId() {
        taskId += 1;
        return taskId;
    }
}
