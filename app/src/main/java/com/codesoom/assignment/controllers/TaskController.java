package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskBuilder;
import com.codesoom.assignment.models.TaskRequestForm;
import com.codesoom.assignment.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Task> list() {
        return taskService.getTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody @Valid TaskRequestForm taskRequestForm) {
        TaskRequestForm taskForm= modelMapper.map(taskRequestForm, TaskRequestForm.class);
        TaskBuilder taskBuilder = new TaskBuilder().title(taskForm.getTitle());
        return taskService.addTask(taskBuilder.build());
    }

    @GetMapping("/{id}")
    public Task read(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody @Valid TaskRequestForm taskRequestForm) {
        TaskRequestForm taskForm= modelMapper.map(taskRequestForm, TaskRequestForm.class);
        TaskBuilder taskBuilder = new TaskBuilder().title(taskForm.getTitle());
        return taskService.updateTask(id, taskBuilder.build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
