package com.codesoom.assignment.task.controller;

import com.codesoom.assignment.task.model.domain.Task;
import com.codesoom.assignment.task.model.request.TaskRequest;
import com.codesoom.assignment.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable final long id) {
        return taskService.findById(id);
    }

    @GetMapping
    public Set<Task> findAll() {
        return taskService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Task save(@RequestBody final TaskRequest taskRequest) {
        Task task = Task.from(taskRequest.getTitle());
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    public Task updateById(@PathVariable final long id,
                                           @RequestBody final TaskRequest taskRequest) {
        Task task = Task.of(id, taskRequest.getTitle());
        return taskService.updateById(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable final long id) {
        taskService.deleteById(id);
    }

}
