package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.TaskDto;
import com.codesoom.assignment.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("")
    public Collection<TaskDto> getTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return service.getTask(id);
    }

    @PostMapping("")
    public TaskDto postTask(@RequestBody TaskDto dto) {
        return service.createNewTask(dto);
    }

    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public TaskDto editTask(@PathVariable Long id, @RequestBody TaskDto dto) {
        dto.setId(id);
        return service.changeTitle(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        TaskDto dto = service.deleteTask(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
