package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public Collection<Task> findAllTasks() {
        return Collections.unmodifiableCollection(taskMap.values());
    }

    public Optional<Task> findById(Long id) {
        final Task task = taskMap.get(id);
        return Optional.ofNullable(task);
    }

    public Task addTask(Task task) {
        taskMap.put(task.getId(), task);
        return task;
    }

    public Optional<Task> changeTitle(Task task) {
        if (!taskMap.containsKey(task.getId())) {
            return Optional.empty();
        }

        taskMap.put(task.getId(), task);
        return Optional.of(task);
    }

    public Optional<Task> deleteById(Long id) {
        final Task removedTask = taskMap.remove(id);
        return Optional.ofNullable(removedTask);
    }
}
