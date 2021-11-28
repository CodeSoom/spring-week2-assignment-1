package com.codesoom.assignment.controllers;

import com.codesoom.assignment.Exception.TaskNotFoundException;
import com.codesoom.assignment.TaskManager;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * HTTP 프로토콜을 통한 Task 처리 컨트롤을 담당합니다.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private TaskManager taskManager = new TaskManager();

    @GetMapping("/**")
    public ResponseEntity<List<Task>> getAll() {
        return new ResponseEntity<>(taskManager.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getOne(@PathVariable Long taskId) {
        Task task = taskManager.getOne(taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/**")
    public ResponseEntity<Task> create(@RequestBody Task task) {
        String title = task.getTitle();

        Task newTask = taskManager.create(title);

        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    @PatchMapping("/{taskId}")
    public ResponseEntity<?> update(@PathVariable Long taskId, @RequestBody Task task) {
        String title = task.getTitle();

        taskManager.update(taskId, title);
        Task updatedTask = taskManager.getOne(taskId);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> delete(@PathVariable Long taskId) {
        taskManager.remove(taskId);

        return new ResponseEntity<>("Success Remove Task", HttpStatus.NO_CONTENT);
    }
}

