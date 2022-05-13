package com.codesoom.assignment.service;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskFinishService {

    private final TaskRepository taskRepository;

    public TaskFinishService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void finish(Long id) {
        taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        taskRepository.delete(id);
    }
}
