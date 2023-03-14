package com.codesoom.assignment.controllers;

import com.codesoom.assignment.config.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
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
    public List<Task> getList() {
        return taskService.getItems();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Integer id) throws TaskNotFoundException {
        return taskService.getTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PatchMapping("/{id}")
    public void PatchTask(@PathVariable Integer id, @RequestBody Task task) throws TaskNotFoundException {
        taskService.update(id, task.getTitle());
    }

    @PutMapping("/{id}")
    public void PutTask(@PathVariable Integer id, @RequestBody Task task) throws TaskNotFoundException {
        taskService.update(id, task.getTitle());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws TaskNotFoundException {
        taskService.delete(id);
    }
}
