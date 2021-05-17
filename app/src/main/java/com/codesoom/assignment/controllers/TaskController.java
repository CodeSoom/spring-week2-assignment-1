package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.application.InMemoryTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("tasks")
public class TaskController {
    private final InMemoryTaskRepository TaskRepository;

    @Autowired
    public TaskController(InMemoryTaskRepository taskRepository) {
        TaskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> tasks() {
        return TaskRepository.fetchAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return TaskRepository.createOne(task);
    }

    @GetMapping("/{id}")
    public Task task(@PathVariable Long id) {
        return TaskRepository.fetchOne(id);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Task updateByPut(@PathVariable Long id, @RequestBody Task source) {
        return TaskRepository.updateOne(id, source);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        TaskRepository.deleteOne(id);
    }
}
