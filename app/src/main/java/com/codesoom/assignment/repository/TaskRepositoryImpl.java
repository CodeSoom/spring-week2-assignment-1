package com.codesoom.assignment.repository;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.model.UpdateTask;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private static final ConcurrentHashMap<Long, Task> database = new ConcurrentHashMap<>();
    private static final AtomicLong seq = new AtomicLong(0L);

     @Override
    public Task save(Task task) {
        task.setId(seq.getAndIncrement());
        database.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Task> findById(Long id) {
        Task task = database.get(id);
        return Optional.ofNullable(task);
    }

    @Override
    public Optional<Task> update(Long id, UpdateTask newTask) {
        return Optional.ofNullable(database.get(id))
                .map(value -> {
                    value.initTitle(newTask.getTitle());
                    return value;
                });
    }

    @Override
    public Optional<Task> delete(Long id) {
        Task removedTask = database.remove(id);
        return Optional.ofNullable(removedTask);
    }
}
