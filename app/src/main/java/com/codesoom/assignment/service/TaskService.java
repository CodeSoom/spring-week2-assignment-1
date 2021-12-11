package com.codesoom.assignment.service;


import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new TaskRepository();
    }

    public Task findByTaskId(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new TaskNotFoundException(id);
        }

        return task.get();
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updatesTask(Long id, Task source) {
        Task target = findByTaskId(id);
        return taskRepository.update(target, source);
    }

    public void removeTask(Long id) {
        Task task = findByTaskId(id);
        taskRepository.delete(task);
    }
}
