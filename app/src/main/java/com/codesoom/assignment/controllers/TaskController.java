package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  private List<Task> tasks = new ArrayList<>();

  @GetMapping
  public List<Task> taskList() {
    return tasks;

  }
}
