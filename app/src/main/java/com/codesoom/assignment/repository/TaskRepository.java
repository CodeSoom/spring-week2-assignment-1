package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {
    private static final Map<Long, Task> tasks = new HashMap<>();
    private static Long sequence = 1L;

    private static final TaskRepository instance = new TaskRepository();

    public static TaskRepository getInstance() {
        return instance;
    }

    public TaskRepository() {
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public Task save(Task task) {
        task.setId(sequence);
        tasks.put(task.getId(), task);

        sequence++;
        return task;
    }

    public Task update(Long id, Task newTask) {
        newTask.setId(id);
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public void delete(Long id) {
        tasks.remove(id);
    }
}
