package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> taskList = new ArrayList<>();
    private Long newTaskId = 0L;

    @GetMapping("")
    public ResponseEntity getTaskList() {
        return new ResponseEntity(taskList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable Long id) {
        Optional<Task> searchTask = taskList.stream()
                                            .filter(task -> task.getId().equals(id))
                                            .findFirst();
        if (!searchTask.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(searchTask.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity createTask(@RequestBody Task task) {
        task.setId(generateId());
        taskList.add(task);

        return new ResponseEntity(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody Task requestTask) {
        Optional<Task> searchTask = taskList.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
        if (!searchTask.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        searchTask.get().setTitle(requestTask.getTitle());
        return new ResponseEntity(searchTask.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        Optional<Task> searchTask = taskList.stream()
                                            .filter(task -> task.getId().equals(id))
                                            .findFirst();
        if (!searchTask.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        taskList.remove(searchTask.get());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private Long generateId() {
        newTaskId = newTaskId + 1;
        return newTaskId;
    }
}
