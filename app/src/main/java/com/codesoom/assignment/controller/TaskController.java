package com.codesoom.assignment.controller;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.entity.Task;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTaskList() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTask(@PathVariable Long id) {
        return new TaskDto(taskService.getTask(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto addTask(@RequestBody TaskDto taskDto) {
        return new TaskDto(taskService.addTask(taskDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto putTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return new TaskDto(taskService.updateTask(id, taskDto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto patchTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return new TaskDto(taskService.updateTask(id, taskDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = TaskNotFoundException.class)
    public String taskNotFoundExceptionHandler(TaskNotFoundException exception) {
        return exception.getMessage();
    }
}
