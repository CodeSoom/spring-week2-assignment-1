package com.codesoom.assignment.domain;

import com.codesoom.assignment.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private Long id = 0L;

    public Task findOne(Task task) {
        return tasks.stream()
                .filter(source -> source.getId() == task.getId())
                .findFirst()
                .orElseThrow();
    }

    public List<Task> findAll() {
        return tasks;
    }

    public Task create(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task update(Task task) {
        Task source = findOne(task);
        source.setTitle(task.getTitle());
        return source;
    }

    public void delete(Task task) {
        Task deleteTarget = findOne(task);
        tasks.remove(deleteTarget);
    }

    /**
     * id 자동 생성
     * @return id
     */
    private Long generateId() {
        return id++;
    }
}
