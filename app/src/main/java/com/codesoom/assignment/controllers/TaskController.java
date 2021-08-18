package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.TodoRepository;
import com.codesoom.assignment.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 이 클래스는 Task 작업에 대한  Rest API를 제공한다.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    Logger logger = Logger.getLogger(this.getClass().getName());
    private final TodoRepository todoRepository;

    public TaskController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Task> findAll() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task findOne(@PathVariable Long id) {
        return  todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return todoRepository.save(task);
    }

    @RequestMapping(path = "/{id}", method = {PUT, PATCH})
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        final Task findTask = todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        findTask.updateTitle(task.getTitle());

        return findTask;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException(EntityNotFoundException err) {
        logger.severe(String.format("[%s]%s", LocalDateTime.now(), err.getMessage()));
    }
}
