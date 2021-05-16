package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskRepository;
import com.codesoom.assignment.exception.ParameterEmptyException;
import com.codesoom.assignment.exception.TaskIdNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private TaskRepository taskRepository;

   public TaskController() {
       taskRepository = new TaskRepository();
    }

    @GetMapping
    public List<Task> taskList() {
        return taskRepository.getTasks();
    }

    @GetMapping("/{id}")
    public Task taskDetail(@PathVariable Long id) {
        System.out.println(this.taskRepository);
        return taskRepository.getTask(id);
    }

    @PostMapping
    public ResponseEntity<Task> taskCreate(@RequestBody Task task) throws Throwable {

        taskRepository.createTask(task);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(task.getId())
                .toUri();
        return new ResponseEntity<Task>(task,  getHttpLocationHeaders(uri), HttpStatus.CREATED);
    }

    private HttpHeaders getHttpLocationHeaders(URI uri) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return httpHeaders;
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Task taskModify(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.updateTask(id,task);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void taskDelete(@PathVariable Long id) {
        taskRepository.removeTask(id);
    }

}
