package com.codesoom.assignment.models;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskRepository {
    private final List<Map<Long, Task>> tasks = new ArrayList<>();
    private final Map<Long, Task> taskMap = new HashMap<>();
    private Long id = 0L;

    public List<Map<Long, Task>> getTasks() {
        return tasks;
    }

    public Optional<Task> getTask(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        taskMap.put(task.getId(), task);
        if (tasks.isEmpty()) {
            tasks.add(taskMap);
        }

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
        System.out.println("id = " + id);
        System.out.println("task = " + getTask(id));
       return Optional.ofNullable(taskMap.remove(id));
    }

    private Long generateId() {
        return ++id;
    }
}
