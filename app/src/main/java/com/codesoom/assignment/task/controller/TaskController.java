package com.codesoom.assignment.task.controller;

import com.codesoom.assignment.task.model.domain.Task;
import com.codesoom.assignment.task.model.request.TaskRequest;
import com.codesoom.assignment.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable final long id) {
        return ok(taskService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Set<Task>> findAll() {
        return ok(taskService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Task> save(@RequestBody final TaskRequest taskRequest) {
        Task task = Task.from(taskRequest.getTitle());
        return ok(taskService.save(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateById(@PathVariable final long id,
                                           @RequestBody final TaskRequest taskRequest) {
        Task task = Task.of(id, taskRequest.getTitle());
        return ok(taskService.updateById(id, task));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final long id) {
        taskService.deleteById(id);
    }

}
