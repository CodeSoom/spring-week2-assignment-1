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
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        // TODO title 없을 시 저장할 수 없도록
        return taskService.create(task);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task findOne(@PathVariable Long id) {
        return taskService.findOne(id);
    }

    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@RequestBody Task task) {
        return taskService.update(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
