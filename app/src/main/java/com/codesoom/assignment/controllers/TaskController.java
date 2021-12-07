package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity list() {

        return ResponseEntity.ok(taskService.findALL());
    }

    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        Optional<Task> taskOptional = taskService.findByTaskId(id);

        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(taskOptional);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Task task) {
        Task newTask = taskService.saveTask(task);

        WebMvcLinkBuilder selfLinkBuilder = linkTo(TaskController.class).slash(newTask.getId());
        URI createdUri = selfLinkBuilder.toUri();

        return ResponseEntity.created(createdUri).body(newTask);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity update(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> taskOptional = taskService.findByTaskId(id);

        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task updateTask = taskService.updateTask(taskOptional.get(), task);

        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Task> taskOptional = taskService.findByTaskId(id);

        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        taskService.removeTask(taskOptional.get());

        return ResponseEntity.noContent().build();
    }
}
