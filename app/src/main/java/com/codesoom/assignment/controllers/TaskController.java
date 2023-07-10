package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.models.domain.Task;
import com.codesoom.assignment.models.request.TaskCreate;
import com.codesoom.assignment.models.request.TaskEdit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> list() {
        return taskService.getTaskList();
    }

    @GetMapping("/tasks/{id}")
    public Task detail(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> create(@RequestBody TaskCreate taskCreate) {
        taskCreate.validate();
        Task task = taskService.createTask(taskCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @RequestMapping(value = "/tasks/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task update(@PathVariable Long id,@RequestBody TaskEdit task) {
        task.setId(id);
        return taskService.updateTask(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(id == null) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
