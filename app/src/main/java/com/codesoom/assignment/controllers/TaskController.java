package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.TaskForm;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TaskAddService;
import com.codesoom.assignment.service.TaskFinishService;
import com.codesoom.assignment.service.TaskUpdateService;
import com.codesoom.assignment.service.TaskQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final TaskFinishService taskFinishService;
    private final TaskAddService taskAddService;
    private final TaskUpdateService taskUpdateService;
    private final TaskQueryService taskQueryService;

    public TaskController(TaskFinishService taskFinishService, TaskAddService taskAddService, TaskUpdateService taskUpdateService, TaskQueryService taskQueryService) {
        this.taskFinishService = taskFinishService;
        this.taskAddService = taskAddService;
        this.taskUpdateService = taskUpdateService;
        this.taskQueryService = taskQueryService;
    }

    @GetMapping
    public List<Task> findTasks() {
        return taskQueryService.findTasks();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task addTask(@RequestBody TaskForm taskForm) {
        return taskAddService.add(taskForm);
    }

    @GetMapping("/{id}")
    public Task findTask(@PathVariable Long id) {
        return taskQueryService.findTask(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void finishTask(@PathVariable Long id) {
        taskFinishService.finish(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Task updateTask(@PathVariable Long id, @RequestBody TaskForm taskForm) {
        return taskUpdateService.update(id, taskForm);
    }

}
