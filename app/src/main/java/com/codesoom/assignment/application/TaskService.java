package com.codesoom.assignment.application;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    public TaskService() {
        taskRepository = new TaskRepository();
    }

    // Task all
    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    // Task one
    public Task getTask(Long id) {
        return taskRepository.getTask(id);
    }

    // Task Insert
    public Task createTask(Task task) {
        return taskRepository.createTask(task);
    }

    // Task Update
    public Task updateTask(Long id, Task task) {
        return taskRepository.updateTask(id, task);
    }

    // Task Delete
    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }
}
