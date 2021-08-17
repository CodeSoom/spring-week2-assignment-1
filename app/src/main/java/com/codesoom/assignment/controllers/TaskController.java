package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.TodoRepository;
import com.codesoom.assignment.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TodoRepository todoRepository;

    @Autowired
    public TaskController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(todoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findOne(@PathVariable long id) {
        final Task findTask = todoRepository.findById(id).orElseThrow(NotFoundEntityException::new);

        return ResponseEntity.ok(findTask);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        final Task savedTask = todoRepository.save(task);
        return ResponseEntity.created(URI.create("/tasks/" + savedTask.getId())).body(savedTask);
    }

    @RequestMapping(path = "/{id}", method = {PUT, PATCH})
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task task) {
        final Task findTask = todoRepository.findById(id).orElseThrow(NotFoundEntityException::new);
        findTask.updateTitle(task.getTitle());

        return ResponseEntity.ok().body(findTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {
        todoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<String> handleNotFoundEntityException() {
        return ResponseEntity.notFound().build();
    }
}
