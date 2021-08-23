package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin


public class TaskController {
    @Autowired
    TaskService taskService;

    private List<Task> tasks = new ArrayList<>();

    @GetMapping()
    public List<Task> tasks(){
        return taskService.getTasks();
    }

    @GetMapping("{id}")
    public Task taskDetail(@PathVariable("id") int id){
        return taskService.getTask(id);
    }

    @PostMapping()
    public Task createTask(@RequestBody Task requestTask){
        return taskService.createTask(requestTask);
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable("id") int id, @RequestBody Task requestTask){
        return taskService.updateTask(id, requestTask);
    }

    @PatchMapping("{id}")
    public Task updateTask2(@PathVariable("id") int id, @RequestBody Task requestTask){
        return taskService.updateTask(id, requestTask);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable("id") int id){
        taskService.deleteTask(id);
    }
}
