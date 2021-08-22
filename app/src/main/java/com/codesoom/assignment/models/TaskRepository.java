package com.codesoom.assignment.models;

import com.codesoom.assignment.TaskNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskRepository {
    private final Map<Long, Task> tasks = new HashMap<>();
    private Long id = 0L;

    public Collection<Task> getTasks() {
        return tasks.values();
    }

    public Task getTask(Long id) {
        return Optional
                .ofNullable(tasks.get(id))
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task saveTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);

        return task;
    }

    public Task updateTask(Long id, Task source) {
        Task task = getTask(id);
        task.setTitle(source.getTitle());

        return task;
    }

    public void deleteTask(Long id) {
        Optional
                .ofNullable(tasks.remove(id))
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    private synchronized Long generateId() {
        return ++id;
    }
}
