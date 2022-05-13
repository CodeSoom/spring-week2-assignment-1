package com.codesoom.assignment.service;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskAddService {

    private final TaskRepository taskRepository;

    public TaskAddService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task add(Task task) {
        return taskRepository.save(task);
    }
}
