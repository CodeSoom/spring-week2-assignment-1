package com.codesoom.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;
import com.codesoom.assignment.service.TaskService;

@RestController
public class TaskController {
    private TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<TasksDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TasksDTO tasksDTO = taskService.createTask(taskDTO);
        return ResponseEntity.ok().body(tasksDTO);
    }
    @GetMapping("/tasks")
    public ResponseEntity<TasksDTO> getTask() {
        TasksDTO tasksDTO = taskService.getTasks();
        return ResponseEntity.ok().body(tasksDTO);
    }
}
