package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public Collection<Task> findAllTasks() {
        return Collections.unmodifiableCollection(taskMap.values());
    }

    @Override
    public Optional<Task> findById(Long id) {
        final Task task = taskMap.get(id);
        return Optional.ofNullable(task);
    }

    @Override
    public Task addTask(Task task) {
        taskMap.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<Task> deleteById(Long id) {
        final Task removedTask = taskMap.remove(id);
        return Optional.ofNullable(removedTask);
    }

    @Override
    public Map<Long, Task> deleteTasks(Set<Long> idSet) {
        final Map<Long, Task> removedTasks = new ConcurrentHashMap<>();

        for (Long id : idSet) {
            Task removedTask = taskMap.remove(id);
            if (removedTask == null) {
                continue;
            }
            removedTasks.put(id, removedTask);
        }

        return removedTasks;
    }
}
