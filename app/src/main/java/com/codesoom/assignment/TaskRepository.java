package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepository {

    public Map<Long, Task> taskStore = new HashMap<>();
    AtomicLong idCounter = new AtomicLong(0);

    public Long generateId() {
        return idCounter.getAndIncrement();
    }

    public Collection<Task> getTasks() {
        return taskStore.values();
    }

    public Task findTaskWithIdInTasks(Long id) {
        return taskStore.get(id);
    }

    public Task createTask(Long id, Task task) {
        return taskStore.put(task.getId(), task);
    }

    public Task updateTask(Long id, Task task) {
        return taskStore.replace(task.getId(), task);
    }

    public void deleteTask(Long id){
        taskStore.remove(id);
    }
}
