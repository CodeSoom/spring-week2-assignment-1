package com.codesoom.assignment.service;

import com.codesoom.assignment.entity.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Task> getTask(Long id) {
        Task task = taskRepository.findById(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    public ResponseEntity<Task> addTask(Task task) {
        task.setId(increaseId());
        return taskRepository.save(task);
    }

    public ResponseEntity<Task> updateTask(Long id, Task inputTask) {
        Task task = taskRepository.findById(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        task.setTitle(inputTask.getTitle());
        return taskRepository.save(task);
    }

    public ResponseEntity<Task> deleteTask(Long id) {
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
