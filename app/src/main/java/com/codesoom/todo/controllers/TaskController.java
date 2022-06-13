package com.codesoom.todo.controllers;

import com.codesoom.todo.domain.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    // TODO: fill skeleton mapper
    @GetMapping("")
    public String showTasks() {
        return "list";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable("id") int taskId) {
        return String.valueOf(taskId);
    }

    @PostMapping("")
    public Task create (@RequestBody Task task){
        return task;
    }

    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public Task editTitle (@PathVariable ("id") int taskId){
        return new Task();
    }

    @DeleteMapping("/{id}")
    public Task deleteTask (@PathVariable("id") int taskId){
        return new Task();
    }




}
