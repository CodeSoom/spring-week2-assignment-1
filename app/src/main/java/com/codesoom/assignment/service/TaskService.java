package com.codesoom.assignment.service;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> getTasks() {
        return taskRepository.getTasks();
    }

    public Task getTask(Long id) {
        return taskRepository.getTask(id);
    }

    public Task saveTask(Task task){
        return taskRepository.saveTask(task);
    }

    public Task updateTask(Long id, Task task) {
        return taskRepository.updateTask(id, task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }
}
