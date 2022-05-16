package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskForm;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TaskAddService;
import com.codesoom.assignment.service.TaskDoneService;
import com.codesoom.assignment.service.TaskUpdateService;
import com.codesoom.assignment.service.TaskQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final TaskDoneService taskDoneService;
    private final TaskAddService taskAddService;
    private final TaskUpdateService taskUpdateService;
    private final TaskQueryService taskQueryService;

    public TaskController(TaskDoneService taskDoneService, TaskAddService taskAddService, TaskUpdateService taskUpdateService, TaskQueryService taskQueryService) {
        this.taskDoneService = taskDoneService;
        this.taskAddService = taskAddService;
        this.taskUpdateService = taskUpdateService;
        this.taskQueryService = taskQueryService;
    }

    @GetMapping
    public List<TaskDto> findTasks() {
        return taskQueryService.findTasks();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TaskDto addTask(@RequestBody TaskForm taskForm) {
        return taskAddService.add(taskForm);
    }

    @GetMapping("/{id}")
    public TaskDto findTask(@PathVariable Long id) {
        return taskQueryService.findTask(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void finishTask(@PathVariable Long id) {
        taskDoneService.done(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskForm taskForm) {
        return taskUpdateService.update(id, taskForm);
    }

}
