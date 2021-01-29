package com.codesoom.assignment.controller;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.entity.Task;
import com.codesoom.assignment.repository.TaskRepository;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getTaskList() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        TaskDto taskDto = new TaskDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {
        Task addTask = taskService.addTask(taskDto);
        TaskDto responseDto = new TaskDto(addTask);
        return ResponseEntity.created(URI.create("/tasks")).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> putTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task task = taskService.updateTask(id, taskDto);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        TaskDto outputTaskDto = new TaskDto(task);
        return ResponseEntity.ok(outputTaskDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDto> patchTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task task = taskService.updateTask(id, taskDto);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        TaskDto outputTaskDto = new TaskDto(task);
        return ResponseEntity.ok(outputTaskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long id) {
        if (taskRepository.existsById(id)) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
