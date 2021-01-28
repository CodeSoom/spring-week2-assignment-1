package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TasksController {
    TaskManager taskManager = TaskManager.getInstance();

    @GetMapping
    public List<Task> findAll() {
        return taskManager.findAll();
    }

    @GetMapping("{id}")
    public Task findOne(@PathVariable long id) {
        return taskManager.findOne(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task insertOne(@RequestBody Task task) {
        return taskManager.insertOne(task.title());
    }

    @RequestMapping(
            value = "{id}",
            method = {RequestMethod.PUT, RequestMethod.PATCH}
    )
    public Task modifyOne(@PathVariable long id, @RequestBody Task task) {
        return taskManager.modifyOne(id, task.title());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Task deleteOne(@PathVariable long id) {
        return taskManager.deleteOne(id);
    }
}
