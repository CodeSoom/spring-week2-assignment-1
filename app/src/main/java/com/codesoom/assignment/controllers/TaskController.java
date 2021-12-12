package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskRepository taskRepository = new TaskRepository();

    @GetMapping
    public List<Task> list() {
        return taskRepository.findList();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task source) {
        Task task = taskRepository.add(source);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> detail(@PathVariable Long id, HttpServletResponse response) {
        Optional<Task> find = taskRepository.findDetail(id);

        if (find.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }

        return ResponseEntity.ok(find.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task source, HttpServletResponse response) {
        Optional<Task> task = taskRepository.update(id, source.getTitle());

        if (task.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }

        return ResponseEntity.ok(task.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update2(@PathVariable Long id, @RequestBody Task source, HttpServletResponse response) {
        return update(id, source, response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Task> find = taskRepository.findDetail(id);

        if (find.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }

        taskRepository.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

}
