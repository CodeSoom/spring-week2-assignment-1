package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    private Long newId = 0L;

    public TaskRepository() {}

    public void createNewTaskId() {
        this.newId += 1L;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public Optional<Task> findTaskById(Long id) {
        return tasks
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public Long getNewId() {
        return newId;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
