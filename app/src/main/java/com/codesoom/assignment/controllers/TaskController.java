package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final static List<Task> tasks = new ArrayList<>();
    private static int id = 0;

    @GetMapping
    public List<Task> taskList() {
        return tasks;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> task(@PathVariable int id) {
        Optional<Task> findTask = tasks.stream().filter(x->x.getId()==id).findFirst();

        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(findTask.get(), HttpStatus.OK);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        task.setId(getId());
        tasks.add(task);

        return task;
    }

    private int getId() {
        return ++id;
    }
}
