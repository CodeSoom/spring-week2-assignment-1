package com.codesoom.assignment.controller;

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
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id, @RequestBody Task inputTask) {
        return taskService.updateTask(id, inputTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> patchTask(@PathVariable Long id, @RequestBody Task inputTask) {
        return taskService.updateTask(id, inputTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
