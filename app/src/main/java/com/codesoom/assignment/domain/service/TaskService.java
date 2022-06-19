package com.codesoom.assignment.domain.service;

import com.codesoom.assignment.common.exception.ResourceNotFoundException;
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

    public Task getTask(Long id) {
        Optional<Task> foundTask = this.taskRepository.findById(id);
        if (!foundTask.isPresent()) {
            throw new ResourceNotFoundException("Not found with ID " + id);
        }

        return foundTask.get();
    }

    public Task register(Task task) {
        return this.taskRepository.save(task);
    }

    public Task modifyTask(Long id, String newTitle) {
        Optional<Task> task = this.taskRepository.findById(id);
        if (!task.isPresent()) {
            throw new ResourceNotFoundException("FAILED modifying task, because task with ID " + id + " NOT found");
        }

        Task modifiedTask = task.get();
        modifiedTask.setTitle(newTitle);
        return this.taskRepository.save(modifiedTask);
    }

    public void deleteTask(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        if (!task.isPresent()) {
            throw new ResourceNotFoundException("FAILED deleting task, because task with ID " + id + " NOT found");
        }

        this.taskRepository.delete(task.get());
    }
}
