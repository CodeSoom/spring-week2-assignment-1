package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTask(Long taskId) {
        return taskRepository.get(taskId);
    }

    public Task createTask(String title) {
        return taskRepository.add(title);
    }

    public void deleteTask(Long taskId) {
        taskRepository.remove(taskId);
    }

}
