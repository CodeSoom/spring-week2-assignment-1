package com.codesoom.assignment.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 할 일 목록
 */
public class TasksStorage {
    private final HashMap<Long, Task> tasks = new HashMap<>();
    private Long newId;

    public TasksStorage() {
        newId = 0L;
    }

    public synchronized Task create(String title) {
        Task task = new Task(newId, title);
        tasks.put(task.getId(), task);

        newId++;

        return task;
    }

    public synchronized Task read(Long id) {
        return Optional
                .ofNullable(tasks.get(id))
                .orElseThrow();
    }

    public synchronized Collection<Task> readAll() {
        return tasks.values();
    }

    public synchronized Task update(Long id, String title) {
        if (!tasks.containsKey(id)) {
            throw new NoSuchElementException();
        }

        Task task = new Task(id, title);

        tasks.replace(id, task);

        return task;
    }

    public synchronized Task delete(Long id) {
        return Optional
                .ofNullable(tasks.remove(id))
                .orElseThrow();
    }
}
