// TODO
// 4. Update - PUT/PATCH /tasks/{id}
// 5. DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codesoom.assignment.model.Task;

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

    @PostMapping
    public Task create(@RequestBody Task task) {
        if (task.getTitle().isBlank()) {
            // TODO: validation error....
        }
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        Task task = findTask(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }


    private Task findTask(Long id) {
        return tasks.stream()
            .filter(task -> task.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
