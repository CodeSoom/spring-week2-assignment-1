package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private Long newId = 0L;
    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> read(@PathVariable Long id) {
        try {
            Task task = tasks.stream().filter(item -> item.getId().equals(id))
                    .findFirst()
                    .get();
            return new ResponseEntity<>(task, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity update(@PathVariable Long id, @RequestBody Task sourceTask) {
        try {
            Task task = tasks.stream().filter(item -> item.getId().equals(id)).findFirst().get();
            task.setTitle(sourceTask.getTitle());
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            Task task = tasks.stream().filter(item -> item.getId().equals(id))
                    .findFirst()
                    .get();
            tasks.remove(task);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

}
