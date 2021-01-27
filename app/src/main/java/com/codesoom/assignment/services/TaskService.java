package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> readTasks() {
        return taskRepository.findAll();
    }

    public Task readTask(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.create(task);
    }

    public Task updateTask(Long id, @RequestBody Task task) {
        return taskRepository.update(id, task);
    }

    public Task deleteTask(Long id) {
        return taskRepository.delete(id);
    }

    public boolean readTaskIsNull(Long id){
        return readTask(id) == null;
    }

}
