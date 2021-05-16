// TODO
// 1. Read Collection - GET /tasks [OK]
// 2. Read Item - GET /tasks/{id} [OK]
// 3. Create - POST /tasks/{id} [OK]
// 4. Update - PUT/PATCH /tasks/{id} [OK]
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.InMemoryTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Task> task(@PathVariable Long id) {
        return ResponseEntity.of(TaskRepository.fetchOne(id));
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
    public ResponseEntity<Task> update(
            @PathVariable Long id,
            @RequestBody Task source
    ) {
        Optional<Task> entity = TaskRepository.fetchOne(id);

        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = entity.get();
        task.setTitle(source.getTitle());

        return ResponseEntity.of(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        Optional<Task> entity = TaskRepository.fetchOne(id);

        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskRepository.deleteOne(entity.get());

        return ResponseEntity.noContent().build();
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
