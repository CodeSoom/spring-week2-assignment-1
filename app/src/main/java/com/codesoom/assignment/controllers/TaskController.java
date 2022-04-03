package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.dtos.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task saveTask(@RequestBody TaskDto taskDto) {
        return taskRepository.save(taskDto);
    }

    @GetMapping("/{id}")
    public Task findTask(@PathVariable long id) {
        return taskRepository.find(id);
    }

    @GetMapping("")
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Task updateTask(@PathVariable long id, @RequestBody TaskDto taskDto) {
        return taskRepository.update(id, taskDto.getTitle());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id) {
        taskRepository.remove(id);
    }
}
