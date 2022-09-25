package com.codesoom.assignment.repository;

import com.codesoom.assignment.exception.TaskIdNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private static final ConcurrentHashMap<Long, Task> database = new ConcurrentHashMap<>();
    private static final AtomicLong seq = new AtomicLong(0L);
    public static final TaskIdNotFoundException TASK_ID_NOT_FOUND_EXCEPTION = new TaskIdNotFoundException("존재하지않는 아이디입니다.");

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
    public Task findById(Long id) {
        return Optional.ofNullable(database.get(id)).orElseThrow(TASK_ID_NOT_FOUND_EXCEPTION);
    }

    @Override
    public Task update(Task newTask) {
        return Optional.ofNullable(database.get(newTask.getId()))
                .map(value -> {
                    value.setTitle(newTask.getTitle());
                    return value;
                }).orElseThrow(TASK_ID_NOT_FOUND_EXCEPTION);
    }

    @Override
    public Task delete(Long id) {
        Task removedTask = database.remove(id);
        return Optional.ofNullable(removedTask).orElseThrow(TASK_ID_NOT_FOUND_EXCEPTION);
    }
}
