package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.JsonTask;
import com.codesoom.assignment.application.TaskApplicationService;
import com.codesoom.assignment.application.TaskJsonTransfer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskApplicationService taskApplicationService = new TaskApplicationService();
    TaskJsonTransfer transfer = new TaskJsonTransfer();

    @GetMapping
    public String getAllTasks() {
        return transfer.taskListToJson(taskApplicationService.getAllTasks()).orElseThrow();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificTask(@PathVariable Long id) {
        return taskApplicationService.findTask(id)
            .flatMap(
                it -> transfer.taskToJson(it)
            ).map(
                it -> new ResponseEntity<>(it, HttpStatus.OK)
            ).orElse(
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
            );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody JsonTask jsonTask) {
        Long createdTaskId = taskApplicationService.createTask(jsonTask.title);
        return taskApplicationService.findTask(createdTaskId).flatMap(it -> transfer.taskToJson(it)).orElseThrow();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTitle(@PathVariable Long id, @RequestBody JsonTask jsonTask) {
        Optional<Object> result = taskApplicationService.updateTaskTitle(id, jsonTask.title);
        if (result.isEmpty()) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return taskApplicationService.findTask(id)
            .flatMap(
                it -> transfer.taskToJson(it)
            ).map(
                it -> new ResponseEntity<>(it, HttpStatus.OK)
            ).orElse(
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
            );
    }
}
