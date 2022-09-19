package com.codesoom.assignment.controllers;


import com.codesoom.assignment.exception.TaskException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.type.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskId(@PathVariable Long id) throws TaskException {
        for (Task task : tasks
        ) {
            if (task.getId() == id) {
                return new ResponseEntity<>(task, HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {

        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task body) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(body.getTitle());
                return new ResponseEntity<>(task, HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {

        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                return new ResponseEntity<>(task, HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }


    private Long generateId( ) {
        newId += 1;
        return newId;
    }


}
