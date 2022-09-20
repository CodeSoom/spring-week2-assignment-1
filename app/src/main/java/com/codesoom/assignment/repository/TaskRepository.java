package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepository {
    public final static Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private static final AtomicLong id = new AtomicLong();

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }


    public Task save(Task task) {
        task.setId(id.incrementAndGet());
        tasks.put(task.getId(), task);
        return task;
    }

    public Task update(Task task) {
        Task findOne = findById(task.getId());
        findOne.setTitle(task.getTitle());
        return findOne;
    }

    public void delete(Long id) {
        tasks.remove(id);
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public boolean isExist(Long id) {
        return findById(id) != null;
    }
}
