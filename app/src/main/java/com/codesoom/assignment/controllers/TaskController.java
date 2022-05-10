package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.TaskDTO;
import com.codesoom.assignment.dto.TasksDTO;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<TasksDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok().body(taskService.createTask(taskDTO));
    }

    @GetMapping("/tasks")
    public ResponseEntity<TasksDTO> getTask() {
        return ResponseEntity.ok().body(taskService.getTasks());
    }

    @CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true")
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody String title, @PathVariable long id) {
        return ResponseEntity.ok().body(taskService.updateTasks(id, title));
    }
}
