package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private Long id = 0L;

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Task getTask(Long id) {
        return taskRepository.getTask(id);
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        return taskRepository.createTask(task);
    }

    public Task updateTask(Long id, Task task) {
        return taskRepository.updateTask(id, task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }

    private Long generateId() {
        id += 1L;

        return id;
    }

}
