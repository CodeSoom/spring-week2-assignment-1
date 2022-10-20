package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final Map<Long, Task> taskMap = Collections.synchronizedMap(new TreeMap<>(Collections.reverseOrder()));

    public Collection<Task> findAllTasks() {
        return Collections.unmodifiableCollection(taskMap.values());
    }

    @Override
    public List<Task> findRecentlyAddedTasks() {
        final List<Task> recentlyAddedTaskList = new ArrayList<>();

        for (Task task : taskMap.values()) {
            if (recentlyAddedTaskList.size() >= 100) {
                break;
            }

            recentlyAddedTaskList.add(task);
        }

        return recentlyAddedTaskList;
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

        return idSet.stream()
                .map(taskMap::remove)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Task::getId, task -> task));
    }
}
