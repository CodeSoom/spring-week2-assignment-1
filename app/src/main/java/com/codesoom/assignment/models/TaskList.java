package com.codesoom.assignment.models;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * 할 일 목록.
 */
public class TaskList {
    private final Map<Long, Task> tasks = new TreeMap<>();
    private final TaskLongId taskLongId = new TaskLongId();

    public Optional<Task> one(final Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Collection<Task> all() {
        return tasks.values();
    }

    public Collection<Task> descendingAll() {
        final var descendingTasks = ((TreeMap<Long, Task>) tasks).descendingMap();
        return descendingTasks.values();
    }

    public Task save(final Task task) {
        final var newTaskId = taskLongId.newId();
        final var newTask = new Task(newTaskId, task.getTitle());
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public void update(final Long id, final Task task) {
        final var newTask = new Task(id, task.getTitle());
        tasks.replace(id, newTask);
    }

    public Optional<Task> remove(final Long id) {
        return Optional.ofNullable(tasks.remove(id));
    }
}
