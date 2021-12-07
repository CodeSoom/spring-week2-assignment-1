package com.codesoom.assignment.service;


import com.codesoom.assignment.domain.Task;
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

    public Optional<Task> findByTaskId(long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findALL() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task, Task source) {
        task.setTitle(source.getTitle());

        return task;
    }

    public void removeTask(Task task) {
        taskRepository.delete(task);
    }
}
