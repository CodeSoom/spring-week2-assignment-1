package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.models.domain.Task;
import com.codesoom.assignment.models.request.TaskCreate;
import com.codesoom.assignment.models.request.TaskEdit;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> list() {
        return taskService.getTaskList();
    }

    @GetMapping("/tasks/{id}")
    public Task detail(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tasks")
    public Task create(@RequestBody TaskCreate taskCreate) {
        taskCreate.validate();
        return taskService.createTask(taskCreate);
    }

    @RequestMapping(value = "/tasks/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task update(@PathVariable Long id, @RequestBody TaskEdit task) {
        task.setId(id);
        return taskService.updateTask(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
