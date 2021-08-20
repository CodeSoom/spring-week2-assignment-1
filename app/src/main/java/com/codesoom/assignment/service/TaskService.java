package com.codesoom.assignment.service;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Map<Long, Task>> getTasks() {
        return taskRepository.getTasks();
    }

    public Optional<Task> getTask(Long id) {
        return taskRepository.getTask(id);
    }

    public Task createTask(Task task){
        return taskRepository.createTask(task);
    }

    public Optional<Task> updateTask(Long id, Task task) {
        return taskRepository.updateTask(id, task);
    }

    public Optional<Task> deleteTask(Long id) {
        return taskRepository.deleteTask(id);
    }
}
