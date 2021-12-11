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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private static Map<Long, Task> tasks = new LinkedHashMap<>();
    private static Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return new ArrayList<>(tasks.values());
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.put(newId, task);

        return task;
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        Optional<Task> find = findTaskFromList(id);

        if (find.isPresent()) {
            Task task = find.get();
            return new ResponseEntity(task, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Task source) {
        Optional<Task> find = findTaskFromList(id);

        if (find.isPresent()) {
            Task task = find.get();
            task.setTitle(source.getTitle());
            return new ResponseEntity(task, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update2(@PathVariable Long id, @RequestBody Task source) {
        return update(id, source);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Task> find = findTaskFromList(id);

        if (find.isPresent()) {
            tasks.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    private Optional<Task> findTaskFromList(Long id) {
        Task task = tasks.get(id);
        return Optional.ofNullable(task);
    }

    private static synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
