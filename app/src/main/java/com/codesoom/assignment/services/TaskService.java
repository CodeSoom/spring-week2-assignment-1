package com.codesoom.assignment.services;

import com.codesoom.assignment.controllers.TaskNotFoundException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task updateTask(Long id, Task source) {
        Task task = getTask(id);
        task.setTitle(source.getTitle());

        return task;
    }

    public void deleteTask(Long id) {
        Task task = getTask(id);
        tasks.remove(task);
    }

    private synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
