package com.codesoom.assignment.controller;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        if (task.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
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
        if (task.hasNotTitle()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        return taskService.create(task);
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
        if (isNotExist(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        } else {
            taskService.delete(id);
        }
    }

    /**
     * PUT/PATCH는 현재 동일한 동작으로 처리하기 위해 로직 공통화
     * @param id
     * @param task
     * @return
     */
    public Task update(Long id, Task task) {
        if (isNotExist(id) || task.hasNotTitle()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        return taskService.update(id, task);
    }

    /**
     * Task id가 존재하는지 조회
     * @param id
     * @return
     */
    private boolean isNotExist(Long id) {
        return taskService.findOne(id).isEmpty();
    }
}
