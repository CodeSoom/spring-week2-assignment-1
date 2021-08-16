package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final List<Map<String, Task>> tasks = new ArrayList<>();
    private final Map<String, Task> taskMap = new HashMap<>();
    private Long newId = 0L;

    @GetMapping
    public List<Map<String, Task>> getTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        Optional<Task> task = findTask(id);
        if (task.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setId(generateId());
        taskMap.put(task.getId() + "", task);
        if (tasks.isEmpty()) {
            tasks.add(taskMap);
        }

        return new ResponseEntity(task, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTaskByPatch(@PathVariable("id") Long id, @RequestBody Task task) {
        Optional<Task> findtask = findTask(id);
        if (findtask.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        findtask.get().setTitle(task.getTitle());

        return new ResponseEntity(findtask, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskByPUT(@PathVariable("id") Long id, @RequestBody Task task) {
        Optional<Task> findtask = findTask(id);
        if (findtask.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        findtask.get().setTitle(task.getTitle());

        return new ResponseEntity(findtask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {
        Optional<Task> findtask = findTask(id);
        if (findtask.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        taskMap.remove(String.valueOf(id));
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private Long generateId() {
        return ++newId;
    }

    private Optional<Task> findTask(Long id) {
        Optional<Task> task = Optional.empty();
        Task findTask = taskMap.get(String.valueOf(id));

        if (findTask == null) {
            return task;
        }
        return task.of(findTask);
    }
}
