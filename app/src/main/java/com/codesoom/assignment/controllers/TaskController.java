package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "")
    private List<Task> readAll() {
        return this.taskService.getTasks();
    }

    @GetMapping(path = "/{id}")
    private Task readOne(@PathVariable("id") Long id) {
        return this.taskService.getTask(id);
    }

    @PostMapping(path = "")
    private Task create(@RequestBody TaskDto taskDto) {
        return this.taskService.createNewTask(taskDto);
    }

    @PatchMapping(path = "/{id}")
    private Task modify(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return this.taskService.modifyTaskById(taskDto);
    }

    @DeleteMapping(path = "/{id}")
    private Task remove(@PathVariable("id") Long id) {
        return this.taskService.deleteTaskById(id);
    }
}
