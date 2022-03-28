package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.services.TaskService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final TaskService service;

    public TaskController() {
        this.service = new TaskService();
    }

    @GetMapping
    public List<Task> getTasks() {
        return service.getTasks();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void addTask(@RequestBody TaskDto taskDto) {
        service.addTask(taskDto);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") Long id) {
        return service.findTaskById(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public void updateTask(@PathVariable(name = "id") Long id,
                           @RequestBody TaskDto taskDto) {
        service.updateTaskById(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") Long id) {
        service.deleteTaskById(id);
    }
}
