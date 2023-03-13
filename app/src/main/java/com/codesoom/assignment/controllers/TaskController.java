package com.codesoom.assignment.controllers;

import com.codesoom.assignment.config.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskList;
import com.codesoom.assignment.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getList() {
        return taskService.getItems();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody String title) throws TaskNotFoundException {
         taskService.update(id, title);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws TaskNotFoundException {
        taskService.delete(id);
    }
}
