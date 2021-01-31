package com.codesoom.assignment.controllers;

import java.util.List;

import com.codesoom.assignment.dto.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.service.TaskService;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> list() {
        return taskService.getTaskList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }

    @PutMapping("/{id}")
    public Task modifyTask(@PathVariable("id") Long id, @RequestBody Task source) {
        return taskService.modifyTask(source, id);
    }

    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable("id") Long id, @RequestBody Task source) {
        return taskService.modifyTask(source, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public DeleteResponse deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new DeleteResponse("delete successfully");
    }

}
