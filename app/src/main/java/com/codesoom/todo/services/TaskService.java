package com.codesoom.todo.services;

import com.codesoom.todo.domain.Task;
import com.codesoom.todo.repository.TaskRepository;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();


    public Task addTask(Task task){
        taskRepository.add(task);
        return task;
    }

    public Long removeTask(Long id){
        // TODO: add exception for not found case
        taskRepository.findById(id).ifPresent(taskRepository::delete);

        // TODO: think about return type of method
        return id;
    }
}
