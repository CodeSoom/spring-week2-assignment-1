package com.codesoom.assignment.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tasks {
    private List<Task> tasks;
    private Long taskId = 1L;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public Tasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        task.setId(generateId());
        this.tasks.add(task);
    }

    public void remove(Task task) {
        this.tasks.remove(task);
    }

    public Optional<Task> findTask(Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    private Long generateId() {
        return taskId++;
    }
}
