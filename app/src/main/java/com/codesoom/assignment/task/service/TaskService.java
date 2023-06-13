package com.codesoom.assignment.task.service;

import com.codesoom.assignment.task.model.domain.Task;
import com.codesoom.assignment.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(final long id) {
        return taskRepository.findById(id);
    }

    public Set<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task save(final Task task) {
        return taskRepository.save(task);
    }

    public Task updateById(final long id, final Task task) {
        return taskRepository.updateById(id, task);
    }

    public void deleteById(final long id) {
        taskRepository.deleteById(id);
    }

}
