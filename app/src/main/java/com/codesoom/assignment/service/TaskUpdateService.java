package com.codesoom.assignment.service;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateService {

    private final TaskRepository taskRepository;

    public TaskUpdateService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task update(Long id, Task source) {
        Task target = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        target.update(source);
        return target;
    }
}
