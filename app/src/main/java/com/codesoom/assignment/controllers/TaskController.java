package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTask() {
        return new ResponseEntity<>(taskService.readTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        if(taskService.ReadTaskIsNull(id)) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(taskService.readTask(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if(taskService.ReadTaskIsNull(id)) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUpdateTask(@PathVariable Long id, @RequestBody Task task) {
        if(taskService.ReadTaskIsNull(id)) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(taskService.putUpdateTask(id, task), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdateTask(@PathVariable Long id, @RequestBody Task task) {
        if(taskService.ReadTaskIsNull(id)) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(taskService.patchUpdateTask(id, task), HttpStatus.OK);
    }
}
