package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 1L;

    @GetMapping
    public ResponseEntity<List<Task>> getTaskList() {
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        System.out.println("get id = " + id);
        Optional<Task> findTask = findTaskById(id);
        return new ResponseEntity(findTask, findTask.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody Task task) {
        generateTaskId();
        task.setId(newId);
        tasks.add(task);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PatchMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        System.out.println(" update id = " + id);
        Optional<Task> findTask = findTaskById(id);
        if (findTask.isPresent()) {
            findTask.get().setTitle(task.getTitle());
            return new ResponseEntity(findTask, HttpStatus.OK);
        } else {
            return new ResponseEntity(findTask, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> completeTask(@PathVariable String id) {
        System.out.println("complete id = " + id);
        Optional<Task> findTask = findTaskById(id);
        if (findTask.isPresent()) {
            tasks.remove(findTask.get());
            return new ResponseEntity(findTask, HttpStatus.OK);
        } else {
            return new ResponseEntity(findTask, HttpStatus.NO_CONTENT);
        }
    }

    public void generateTaskId() {
        newId += 1L;
    }

    private Optional<Task> findTaskById(String id) {
        System.out.println("find Task id = " + id);
        Task task = tasks
                .stream()
                .filter(t -> t.getId().equals(Long.parseLong(id)))
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(task);

    }
}
