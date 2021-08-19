package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.TaskRequest;
import com.codesoom.assignment.model.TaskResponse;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<TaskResponse>> getTaskList() {
        return new ResponseEntity<>(taskService.taskList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        return new ResponseEntity<>(taskService.addTask(taskRequest), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<TaskResponse> modifyTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        taskRequest.setId(id);
        return new ResponseEntity<>(taskService.modifyTask(taskRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
