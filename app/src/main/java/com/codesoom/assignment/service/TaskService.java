package com.codesoom.assignment.service;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.entity.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private Long lastId = 0L;
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id);
    }

    public Task addTask(TaskDto taskDto) {
        taskDto.setId(increaseId());
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    public Task updateTask(Long id, TaskDto inputTaskDto) {
        Task task = taskRepository.findById(id);
        if (Objects.isNull(task)) {
            return null;
        }
        task.setTitle(inputTaskDto.getTitle());
        Task save = taskRepository.save(task);
        return save;
    }

    public void deleteTask(Long id) {
            taskRepository.deleteById(id);
    }

    private Long increaseId() {
        lastId += 1;
        return lastId;
    }
}
