package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();

    @GetMapping("")
    public Collection<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

//    @GetMapping("")
//    public Task getTaskById(@RequestParam Long id) {
//        return taskRepository.getTaskById(id);
//    }

    @PostMapping("")
    public Task createTask(@RequestParam String title) {
        return taskRepository.createTask(title);
    }
}
