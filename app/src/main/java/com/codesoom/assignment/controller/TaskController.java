package com.codesoom.assignment.controller;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task findOne(@PathVariable Long id) {
        Task task = taskService.findOne(id);
        return task;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return taskService.create(task.hasValidTitle());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task put(@PathVariable Long id, @RequestBody Task task) {
        return update(id, task);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task patch(@PathVariable Long id, @RequestBody Task task) {
        return update(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    /**
     * PUT/PATCH는 현재 동일한 동작으로 처리하기 위해 로직 공통화
     * @param id
     * @param task
     * @return
     */
    public Task update(Long id, Task task) {
        return taskService.update(id, task.hasValidTitle());
    }
}
