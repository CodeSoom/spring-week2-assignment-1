package com.codesoom.assignment.repositories;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private Long id = 0L;

    public List<Task> getTasks() {
        return tasks;
    }


    public void deleteTask(Long id) {
        Task task = getTask(id);
        tasks.remove(task);
    }


    public Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
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

    private Long generateId() {
        id++;
        return id;
    }


}
