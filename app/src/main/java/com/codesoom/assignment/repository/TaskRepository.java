package com.codesoom.assignment.repository;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    // Task all
    public List<Task> getTasks() {
        return tasks;
    }

    // Task one
    public Task getTask(Long id) {
        return findTask(id);
    }

    // Task Insert
    public Task createTask(Task task) {
        task.setId(generatedId());
        tasks.add(task);
        return task;
    }

    // Task Update
    public Task updateTask(Long id, Task task) {
        Task entity = findTask(id);
        entity.setTitle(task.getTitle());
        return entity;
    }

    // Task Delete
    public void deleteTask(Long id) {
        Task entity = findTask(id);
        tasks.remove(entity);
    }

    private Long generatedId() {
        newId += 1;
        return newId;
    }

    private Task findTask(Long id) {
        return tasks.stream()
                    .filter(task -> task.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
