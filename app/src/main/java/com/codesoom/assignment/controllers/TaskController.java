package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.InMemoryTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@CrossOrigin
public class TaskController {
    private Long newId = 0L;

    private final InMemoryTaskRepository TaskRepository;

    @Autowired
    public TaskController(InMemoryTaskRepository taskRepository) {
        TaskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> tasks() {
        return TaskRepository.fetchAll();
    }

    @GetMapping("/{id}")
    public Task task(@PathVariable Long id) {
        return TaskRepository.fetchOne(id);
    }

    @PostMapping
    public List<Task> create(@RequestBody Task task) {
        if(task.getTitle().isBlank()) {
            // TODO: Validation error...
        }

        task.setId(generateId());
        return TaskRepository.createOne(task);
    }

    @PatchMapping("/{id}")
    protected Task update(
            @PathVariable Long id,
            @RequestBody Task source
    ) {
        Task task = TaskRepository.fetchOne(id);
        task.setTitle(source.getTitle());

        return task;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        Task task = TaskRepository.fetchOne(id);
        TaskRepository.deleteOne(task);

        return ResponseEntity.noContent().build();
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
