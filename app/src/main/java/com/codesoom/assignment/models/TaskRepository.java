package com.codesoom.assignment.models;

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

    public Optional<Task> getTask(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Task saveTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);

        return task;
    }

    public Optional<Task> updateTask(Long id, Task task) {
        Optional<Task> foundtask = getTask(id);
        if (foundtask.isEmpty()) {
            return foundtask;
        }
        foundtask.get()
                .setTitle(task.getTitle());

        return foundtask;
    }

    public Optional<Task> deleteTask(Long id) {
       return Optional.ofNullable(tasks.remove(id));
    }

    private Long generateId() {
        return ++id;
    }
}
