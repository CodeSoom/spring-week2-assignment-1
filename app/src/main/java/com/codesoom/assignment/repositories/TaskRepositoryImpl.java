package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepositoryImpl implements TaskRepository{
    private ConcurrentHashMap<Long, Task> taskMap = new ConcurrentHashMap<>();

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public Task save(Task task) {
        taskMap.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<Task> findOne(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    @Override
    public void delete(Long id) {
        taskMap.remove(id);
    }

    @Override
    public void removeAll() {
        taskMap.clear();
    }
}
