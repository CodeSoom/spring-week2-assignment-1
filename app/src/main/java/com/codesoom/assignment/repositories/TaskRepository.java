package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private Long id = 0L;

    public List<Task> findAll() {
        return tasks;
    }

    public Task findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().orElseThrow();
    }

    public Task create(Task task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setId(generateId());
        tasks.add(newTask);
        return newTask;
    }

    public Task update(Long id, Task task) {
        Task updateTask = findById(id);
        updateTask.setTitle(task.getTitle());
        return updateTask;
    }

    public Task delete(Long id) {
        Task task = findById(id);
        tasks.remove(task);
        return task;
    }

    public Long generateId() {
        id += 1;
        return id;
    }
}
