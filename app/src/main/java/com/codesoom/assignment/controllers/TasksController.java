package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.repository.TaskRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TasksController {

  private final TaskRepository taskRepository;

  public TasksController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @GetMapping
  public List<Task> getTasks() {
    return taskRepository.findAll();
  }

  @GetMapping("/{id}")
  public Task getTask(@PathVariable Long id) {
    return taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task createTask(@RequestBody CreateTaskDto createTaskDto) {
    Task task = new Task();
    task.setTitle(createTaskDto.getTitle());
    return taskRepository.save(task);
  }

  @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
  public Task updateTitle(@RequestBody UpdateTaskDto updateTaskDto, @PathVariable Long id) {
    return taskRepository.findById(id)
        .map(task -> {
          task.setTitle(updateTaskDto.getTitle());
          return task;
        })
        .orElseThrow(() -> new TaskNotFoundException(id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTask(@PathVariable Long id) {
    if (taskRepository.findById(id).isEmpty()) {
      throw new TaskNotFoundException(id);
    }
    taskRepository.deleteById(id);
  }
}
