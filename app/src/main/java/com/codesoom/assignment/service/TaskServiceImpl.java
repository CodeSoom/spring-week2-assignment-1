package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Collection<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getDetails(Long taskId) {
        return taskRepository.findTask(taskId);
    }

    @Override
    public Task create(Task task) {
        return taskRepository.join(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
       return taskRepository.updateTask(taskId, task);
    }

    @Override
    public void deleteTask(Long taskId) {

        taskRepository.deleteTask(taskId);

    }


}

