package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
        return taskService.handleList();
    }

    @GetMapping("{id}")
    public Object getItem(@PathVariable("id") Long id, HttpServletResponse response) {
        return taskService.handleItem(id, response);
    }

    @PostMapping
    public Object create(@RequestBody Task task, HttpServletResponse response) {
        return taskService.handleCreate(task, response);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Long id,
                         @RequestBody Task source, HttpServletResponse response) {
        return taskService.handleUpdate(id, source, response);
    }

    @PatchMapping("/{id}")
    public Object patch(@PathVariable("id") Long id,
                        @RequestBody Task source, HttpServletResponse response) {
        return taskService.handleUpdate(id, source, response);
    }

    @DeleteMapping(value = {"", "/", "/{id}"})
    public Object delete(@PathVariable(required = false) Long id, HttpServletResponse response) {
        return taskService.handleDelete(id, response);
    }
}
