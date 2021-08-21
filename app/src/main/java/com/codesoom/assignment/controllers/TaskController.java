package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Collection<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{taskId}")
    public Task getDetails(@PathVariable("taskId") Long taskId) {

        Task task = taskService.getDetails(taskId);
        return task;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {

        return taskService.create(task);

    }

    @RequestMapping(value = "/{taskId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable("taskId") Long taskId, @RequestBody Task task){

        Task result = taskService.updateTask(taskId, task);
        return result;

    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("taskId") Long taskId){

        taskService.deleteTask(taskId);

    }

}
