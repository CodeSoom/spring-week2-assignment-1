package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "")
    private ResponseEntity<List<Task>> readAll() {
        List<Task> tasks = this.taskService.getTasks();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity<Task> readOne(@PathVariable("id") Long id) {
        Task task = this.taskService.getTask(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping(path = "")
    private ResponseEntity<Task> create(@RequestBody TaskDto taskDto) {
        Task task = this.taskService.createNewTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity<Task> put(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        Task task = this.taskService.putTaskById(taskDto);
        return ResponseEntity.ok(task);
    }

    @PatchMapping(path = "/{id}")
    private ResponseEntity<Task> patch(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        Task task = this.taskService.patchTaskById(taskDto);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<Void> remove(@PathVariable("id") Long id) {
        this.taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
