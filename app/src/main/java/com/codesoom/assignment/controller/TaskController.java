package com.codesoom.assignment.controller;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.entity.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTaskList() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {
        return taskService.addTask(taskDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> putTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDto> patchTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
