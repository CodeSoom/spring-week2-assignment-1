package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class TaskController {
  private List<Task> tasks = new ArrayList<>();
  Long id =0L;


  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Task> taskList() {
    return tasks;
  }
  
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task targetTask(@PathVariable("id") Long id) {
    if ( findTask(id) == null){
      ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
    return findTask(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task create(@RequestBody Task task) throws Throwable {
    task.setId(generateId());
    tasks.add(task);
    return task;
  }


  @RequestMapping(path="/{id}", method={RequestMethod.PATCH, RequestMethod.PUT})
  @ResponseStatus(HttpStatus.OK)
  public Task rewrite(@RequestBody Task rewrittenTask,@PathVariable("id") Long id) throws Throwable {
    Task targetTask = findTask(id);
    targetTask.setTitle(rewrittenTask.getTitle());
    return targetTask;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id){
    tasks.remove(findTask(id));
    return ;
  }


  private Long generateId() {
    return ++id;
  }

  private Task findTask(Long id) {
    return tasks.stream()
        .filter(task -> task.getId().equals(id))
        .findFirst().orElse(null);
  }
}
