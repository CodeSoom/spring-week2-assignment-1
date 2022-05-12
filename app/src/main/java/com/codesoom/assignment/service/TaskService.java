package com.codesoom.assignment.service;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository  taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task findTask(Long id) {
        return findTaskOrElseThrow(id);
    }

    public void deleteTask(Long id) {
        findTaskOrElseThrow(id);
        taskRepository.delete(id);
    }

    public Task updateTask(Long id, Task source) {
        Task target = findTaskOrElseThrow(id);
        target.update(source);
        return target;
    }

    private Task findTaskOrElseThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
    }
}
