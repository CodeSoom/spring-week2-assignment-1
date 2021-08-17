package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;



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
    public Collection<Task> getAll(HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        return taskService.getAll();
    }

    @GetMapping("/{taskId}")
    public Task getDetails(@PathVariable("taskId") Long taskId,HttpServletResponse response) {

        Optional<Task> task = taskService.getDetails(taskId);
        if(task.isPresent()) {
            response.setStatus(HttpStatus.OK.value());
            return task.get();
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

    }

    @PostMapping
    public Task create(@RequestBody Task task, HttpServletResponse response) {

        response.setStatus(HttpStatus.CREATED.value());
        return taskService.create(task);

    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable("taskId") Long taskId, @RequestBody Task task, HttpServletResponse response){

        Optional<Task> result = taskService.updateTask(taskId, task);
        if(result.isPresent()) {
            response.setStatus(HttpStatus.OK.value());
            return result.get();
        }
        else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

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
