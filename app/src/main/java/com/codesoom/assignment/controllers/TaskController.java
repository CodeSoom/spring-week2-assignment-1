package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  private List<Task> tasks = new ArrayList<>();
  Long id =0L;

  @GetMapping
  public List<Task> taskList() {
    return tasks;
  }
  
  @GetMapping("/{id}")
  public Task targetTask(@PathVariable("id") Long id) {
    return findTask(id);
  }

  @PostMapping
  public Task create(@RequestBody Task task) throws Throwable {
    task.setId(generateId());
    tasks.add(task);
    return task;
  }

  private Long generateId() {
    id++;
    return id;
  }

  private Task findTask(Long id) {
    return tasks.stream()
        .filter(task -> task.getId().equals(id))
        .findFirst().orElse(null);
  }
}
