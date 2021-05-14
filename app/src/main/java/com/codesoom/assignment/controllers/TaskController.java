package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private synchronized Long generateId() {
        newTaskId = newTaskId + 1;
        return newTaskId;
    }
}
