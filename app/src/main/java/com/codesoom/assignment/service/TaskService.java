package com.codesoom.assignment.service;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, Task newTask) {
        notFoundException(id);
        return taskRepository.update(id, newTask);
    }

    public void delete(Long id) {
        notFoundException(id);
        taskRepository.delete(id);
    }

    private void notFoundException(Long id) {
        taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
    }
}
