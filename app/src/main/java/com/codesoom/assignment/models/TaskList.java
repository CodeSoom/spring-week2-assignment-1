package com.codesoom.assignment.models;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * 할 일 목록.
 */
public class TaskList {
    private final Map<Long, Task> tasks = new TreeMap<>();
    private final TaskLongId taskLongId = new TaskLongId();

    public Task one(final Long id) {
        return tasks.get(id);
    }

    public Collection<Task> all() {
        return tasks.values();
    }

    public Collection<Task> descendingAll() {
        var descendingTasks = ((TreeMap<Long, Task>) tasks).descendingMap();
        return descendingTasks.values();
    }

    public Task save(final Task task) {
        final var newTaskId = taskLongId.newId();
        final var newTask = new Task(newTaskId, task.getTitle());
        this.tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public void update(final Long id, final Task task) {
        final var newTask = new Task(id, task.getTitle());
        this.tasks.replace(id, newTask);
    }

    public Task remove(final Long id) {
        return this.tasks.remove(id);
    }
}
