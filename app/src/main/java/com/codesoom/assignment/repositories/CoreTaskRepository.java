package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class CoreTaskRepository implements TaskRepository {

    protected final NavigableMap<Long, Task> taskMap = Collections.synchronizedNavigableMap(new TreeMap<>(Collections.reverseOrder()));

    @Override
    public List<Task> findAllTasks() {
        return List.copyOf(taskMap.values());
    }

    @Override
    public Optional<Task> findById(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException(String.format("%d: 0 또는 음수는 허용되지 않습니다.", id));
        }
        return Optional.ofNullable(taskMap.get(id));
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
    public Set<Task> deleteTasks(Set<Long> idSet) {
        return idSet.stream()
                .map(taskMap::remove)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public int getQuantityStored() {
        return taskMap.size();
    }
}
