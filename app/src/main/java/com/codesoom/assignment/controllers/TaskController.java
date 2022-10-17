package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.TaskDto;
import com.codesoom.assignment.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<TaskDto> getTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        final TaskDto dto = service.getTask(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TaskDto postTask(@RequestBody TaskDto dto) {
        return service.createNewTask(dto);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<TaskDto> editTask(@PathVariable Long id, @RequestBody TaskDto dto) {
        dto.setId(id);
        final TaskDto dtoChanged = service.changeTitle(dto);
        if (dtoChanged == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dtoChanged);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        TaskDto dto = service.deleteTask(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
