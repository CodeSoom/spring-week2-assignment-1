package com.codesoom.assignment.controllers;

import com.codesoom.assignment.error.IdEmptyException;
import com.codesoom.assignment.error.ResponseMessage;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<ResponseMessage> idEmpty(){
        return new ResponseEntity<>(ResponseMessage.ID_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Task> read() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task read(@PathVariable Long id, Exception e) {
       return taskRepository.taskFindId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
       return taskRepository.createTask(task);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        return taskRepository.deleteTask(id);
    }

}
