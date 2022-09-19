package com.codesoom.assignment.service;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.repository.TaskRepository;
import com.codesoom.assignment.repository.TaskRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task insertTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task oldTask) {
        oldTask.setId(id);
        Task findTask = findById(id);
        return findTask != null ? taskRepository.update(oldTask) : null;

    }

    public Task deleteTask(Long id) {
        Task findTask = findById(id);
        return findTask != null ? taskRepository.delete(id) : null;

    }
}
