package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  Long id = 0L;
  private HashMap<Long, Task> tasks = new HashMap();

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Task> taskList() {
    return tasks
        .values()
        .stream()
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task targetTask(@PathVariable("id") Long id) throws HttpClientErrorException {
    if (!tasks.containsKey(id)) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
    System.out.println(tasks.get(id));
    return tasks.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task create(@RequestBody Task task) throws Throwable {
    Long generatedId = generateId();
    task.setId(generatedId);
    tasks.put(generatedId, task);
    return task;
  }


  @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
  @ResponseStatus(HttpStatus.OK)
  public Task rewrite(@RequestBody Task rewrittenTask, @PathVariable("id") Long id)
      throws Throwable {
    Task targetTask = tasks.get(id);
    targetTask.setTitle(rewrittenTask.getTitle());
    return targetTask;
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) throws HttpClientErrorException {
    if (!tasks.containsKey(id)) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
    tasks.remove(id);
  }


  private Long generateId() {
    return ++id;
  }

}
