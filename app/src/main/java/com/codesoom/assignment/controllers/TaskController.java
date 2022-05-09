package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> findTasks() {
        return taskRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/{id}")
    public Task findTask(@PathVariable Long id) {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        taskRepository.delete(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task target) {
        Task source = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        source.setTitle(target.getTitle());
        return source;
    }

}
