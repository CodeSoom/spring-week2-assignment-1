package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.TodoRepository;
import com.codesoom.assignment.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 이 클래스는 Task 작업에 대한  Rest API를 제공한다.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TodoRepository todoRepository;

    public TaskController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(todoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findOne(@PathVariable long id) {
        final Task findTask = todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok(findTask);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        final Task savedTask = todoRepository.save(task);
        return ResponseEntity.created(URI.create("/tasks/" + savedTask.getId())).body(savedTask);
    }

    @RequestMapping(path = "/{id}", method = {PUT, PATCH})
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task task) {
        final Task findTask = todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        findTask.updateTitle(task.getTitle());

        return ResponseEntity.ok().body(findTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {
        todoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException() {
        return ResponseEntity.notFound().build();
    }
}
