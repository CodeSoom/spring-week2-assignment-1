package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();

    @GetMapping
    public Collection<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        if (Optional.ofNullable(taskRepository.getTaskById(id)).isPresent()) {
            return new ResponseEntity<>(taskRepository.getTaskById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskRepository.createTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id, @RequestBody Task task) {
        Task newTask = taskRepository.putTask(id, task);
        return new ResponseEntity<>(newTask, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public Optional<Task> patchTask(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.patchTask(id, task);
    }

    @DeleteMapping("/{id}")
    public Optional<Task> deleteTask(@PathVariable Long id) {
        return taskRepository.deleteTask(id);
    }
}
