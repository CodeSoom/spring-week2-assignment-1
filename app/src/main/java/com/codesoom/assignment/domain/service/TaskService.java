package com.codesoom.assignment.domain.service;

import com.codesoom.assignment.domain.entity.Task;
import com.codesoom.assignment.domain.persistences.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTask() {
        return this.taskRepository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        return this.taskRepository.findById(id);
    }

    public Task register(Task task) {
        return this.taskRepository.save(task);
    }

    public Task modifyTask(Long id, String newTitle) {
        Optional<Task> task = this.taskRepository.findById(id);
        if (!task.isPresent()) {
            return null;
        }

        Task modifiedTask = task.get();
        modifiedTask.setTitle(newTitle);
        return this.taskRepository.save(modifiedTask);
    }

    public Task deleteTask(Long id) {
        return null;
    }
}
