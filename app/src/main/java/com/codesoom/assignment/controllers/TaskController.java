package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.model.UpdateTask;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> list() {
        return taskService.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task findTask(@PathVariable("id") Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task insertTask(@RequestBody Task task) {
        return taskService.insertTask(task);
    }

    @RequestMapping(path = "/tasks/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable("id") Long id, @RequestBody UpdateTask task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
