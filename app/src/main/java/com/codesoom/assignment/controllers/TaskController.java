package com.codesoom.assignment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.service.TaskService;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> list() {
        return taskService.getTaskList();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        if (task.getTitle().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Task createdTask = this.taskService.createTask(task);

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        Task task = taskService.getTask(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> modifyTask(@PathVariable("id") Long id, @RequestBody Task source) {
        Task ret = taskService.getTask(id);
        if (ret == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (source.getTitle().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String title = source.getTitle();
        Task modifiedTask = taskService.modifyTask(id, title);
        return new ResponseEntity<>(modifiedTask, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id) {
        Task task = taskService.getTask(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskService.deleteTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
