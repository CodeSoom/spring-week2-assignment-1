package com.codesoom.assignment.application;

import com.codesoom.assignment.NotFoundException;
import com.codesoom.assignment.models.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(Long id) {
        Task task = findById(id);
        return task;
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    public Task updateTask(Long id, Task newTask) {
        Task oldTask = findById(id);
        oldTask.setTitle(newTask.getTitle());
        return newTask;
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    private Task findById(Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public void deleteTask(Long id) {
        Task task = findById(id);
        tasks.remove(task);
    }
}
