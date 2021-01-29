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

    public ResponseEntity<TaskDto> getTask(Long id) {
        Task task = taskRepository.findById(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        TaskDto taskDto = new TaskDto(task);
        return ResponseEntity.ok(taskDto);
    }

    public ResponseEntity<TaskDto> addTask(TaskDto taskDto) {
        taskDto.setId(increaseId());
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        taskRepository.save(task);
        return ResponseEntity.created(URI.create("/tasks")).body(taskDto);
    }

    public ResponseEntity<TaskDto> updateTask(Long id, TaskDto inputTaskDto) {
        Task task = taskRepository.findById(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        task.setTitle(inputTaskDto.getTitle());
        taskRepository.save(task);
        TaskDto outputTaskDto = new TaskDto(task);
        return ResponseEntity.ok(outputTaskDto);
    }

    public ResponseEntity<TaskDto> deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Long increaseId() {
        lastId += 1;
        return lastId;
    }
}
