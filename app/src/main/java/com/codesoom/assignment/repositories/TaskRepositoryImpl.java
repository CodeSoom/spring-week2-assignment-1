package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final Map<Long, Task> taskMap = Collections.synchronizedMap(new TreeMap<>(Collections.reverseOrder()));
    private final RecentlyAddedTasksFinder recentTasksFinder = new RecentlyAddedTasksFinder((NavigableMap<Long, Task>) taskMap);

    @Override
    public Collection<Task> findAllTasks() {
        return Collections.unmodifiableCollection(taskMap.values());
    }

    @Override
    public Collection<Task> findRecentlyAddedTasks(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(String.format("quantity: %d\n0 또는 음수는 허용되지 않습니다.", quantity));
        }

        return recentTasksFinder.findTasks(quantity);
    }

    @Override
    public Optional<Task> findById(Long id) {
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
}
