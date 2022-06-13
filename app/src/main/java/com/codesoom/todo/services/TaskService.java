package com.codesoom.todo.services;

import com.codesoom.todo.models.Task;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TaskService {
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong atomicID = new AtomicLong(0);

    @Nullable
    public Task getTask(Long id) {
        return this.tasks.get(id);
    }

    public ConcurrentHashMap<Long, Task> getTasks() {
        return this.tasks;
    }

    public boolean isTaskExist(Long id) {
        return this.tasks.containsKey(id);
    }

    public Long addTask(Task newTasks) {
        newTasks.setId(atomicID.incrementAndGet());
        this.tasks.putIfAbsent(atomicID.get(), newTasks);
        return this.atomicID.get();
    }

    public Long editTask(Task newTask) {
        Long id = newTask.getId();
        this.tasks.replace(id, newTask);
        return id;
    }

    public Task deleteTask(Long id) {
        Task deletedTask = getTask(id);
        this.tasks.remove(id);
        return deletedTask;
    }
}
