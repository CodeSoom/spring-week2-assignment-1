package com.codesoom.assignment.controller;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    // TODO 실패시에 ResponseEntity Setting
    // TODO ResponseEntity 객체를 드러나지 않게 사용할 수 있는 방법?
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        // TODO title 없을 시 저장할 수 없도록
        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.findOne(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.update(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
