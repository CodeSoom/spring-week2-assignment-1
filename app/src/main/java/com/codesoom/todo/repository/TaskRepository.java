package com.codesoom.todo.repository;

import com.codesoom.todo.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepository {

    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong atomicID = new AtomicLong(0);

    public Task add(Task task) {
        task.setId(atomicID.incrementAndGet());
        this.tasks.putIfAbsent(atomicID.get(), task);
        return this.tasks.get(atomicID.longValue());
    }

    public Long edit(Task task) {
        Long id = task.getId();
        this.tasks.replace(id, task);
        return id;
    }

    /**
     * @param task is target to be deleted
     * @return deleted task if task with id exist, else, Optional object with null
     */
    public Optional<Task> delete(Task task) {
        Long id = task.getId();
        Optional<Task> deletedTask = findById(id);
        this.tasks.remove(id);
        return deletedTask;
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(this.tasks.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }
}
