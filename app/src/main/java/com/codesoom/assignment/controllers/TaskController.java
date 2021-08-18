package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public ResponseEntity<List<Task>> getTaskList() {
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Optional<Task> findTask = findTaskById(id);

        return new ResponseEntity(findTask, findTask.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody Task task) {
        generateTaskId();
        task.setId(newId);
        tasks.add(task);
        return new ResponseEntity(task, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task requestTask) {

        Optional<Task> task = findTaskById(id);
        task.isPresent();
        if (task.isPresent()) {
            task.get().setTitle(requestTask.getTitle());
            return new ResponseEntity(task, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> completeTask(@PathVariable Long id) {
        Optional<Task> task = findTaskById(id);
        if (task.isPresent()) {
            tasks.remove(task.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public void generateTaskId() {
        newId += 1L;
    }

    private Optional<Task> findTaskById(Long id) {
        return tasks
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}