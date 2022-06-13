package com.codesoom.assignment.service;

import com.codesoom.assignment.entity.Task;
import com.codesoom.assignment.persistences.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskManager {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAllTask() {
        return this.taskRepository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        return this.taskRepository.findById(id);
    }

    public Task register(Task task) {
        return this.taskRepository.save(task);
    }

    public Task modifyTask(Long id) {
        return null;
    }

    public Task deleteTask(Long id) {
        return null;
    }
}
