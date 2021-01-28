package com.codesoom.assignment.service;

import java.util.ArrayList;
import java.util.List;

import com.codesoom.assignment.model.Task;

public class TaskServiceImpl implements TaskService {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @Override
    public List<Task> getTaskListService() {
        return tasks;
    }

    @Override
    public Task createTaskService(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @Override
    public Task getTaskService(Long id) {
        Task task = findTask(id);
        return task;
    }

    @Override
    public Task modifyTaskService(Long id, String title) {
        Task task = findTask(id);
        task.setTitle(title);
        return task;
    }

    @Override
    public void deleteTaskService(Task task) {
        tasks.remove(task);
    }

    private Task findTask(Long id) {
        return tasks.stream()
            .filter(task -> task.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
