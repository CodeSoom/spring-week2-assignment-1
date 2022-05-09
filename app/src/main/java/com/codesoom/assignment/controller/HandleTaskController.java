package com.codesoom.assignment.controller;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.HandleTaskService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class HandleTaskController {

    private HandleTaskService handleTaskService;

    public HandleTaskController(HandleTaskService handleTaskService) {
        this.handleTaskService = handleTaskService;
    }

    @GetMapping
    public List<Task> list() {
        return handleTaskService.findAll();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return handleTaskService.create(task);
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return handleTaskService.update(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Task task = new Task();
        task.setId(id);
        handleTaskService.delete(task);
    }
}
