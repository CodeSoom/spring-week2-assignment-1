package com.codesoom.assignment.domain;

import java.util.*;

public class Tasks {
    private Map<Long, Task> tasks;
    private Long taskId = 1L;

    public Tasks() {
        this.tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        Long generateId= generateId();
        task.setId(generateId);
        tasks.put(generateId, task);
    }

    public void remove(Long id) {
        this.tasks.remove(id);
    }

    public Optional<Task> findTask(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public int size() {
        return tasks.size();
    }

    private Long generateId() {
        return taskId++;
    }
}
