package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Task> getAll(HttpServletResponse response) {
        return taskService.getAll();
    }

    @GetMapping("/{taskId}")
    public Task getDetails(@PathVariable("taskId") Long taskId,HttpServletResponse response) {

        Optional<Task> task = taskService.getDetails(taskId);
        if(!task.isPresent()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        response.setStatus(HttpStatus.OK.value());
        return task.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {

        return taskService.create(task);

    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable("taskId") Long taskId, @RequestBody Task task, HttpServletResponse response){

        Optional<Task> result = taskService.updateTask(taskId, task);
        if(!result.isPresent()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        response.setStatus(HttpStatus.OK.value());
        return result.get();

    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId, HttpServletResponse response){

        if(taskService.deleteTask(taskId)) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }

    }

}
