package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> taskList = new ArrayList<>();
    private Long newTaskId = 0L;

    @GetMapping("")
    public ResponseEntity getTaskList() {
        return new ResponseEntity(taskList, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity getTask(@PathVariable Long taskId) {
        Task searchTask = findTask(taskId);

        return new ResponseEntity(searchTask, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity createTask(@RequestBody Task task) {
        task.setId(generateId());
        taskList.add(task);

        return new ResponseEntity(task, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity updateTask(@PathVariable Long taskId, @RequestBody Task requestTask) {
        Task searchTask = findTask(taskId);

        searchTask.setTitle(requestTask.getTitle());
        return new ResponseEntity(searchTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Long taskId) {
        Task searchTask = findTask(taskId);

        taskList.remove(searchTask);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private Task findTask(Long taskId) {
        return taskList.stream()
                       .filter(task -> task.getId().equals(taskId))
                       .findFirst()
                       .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    private synchronized Long generateId() {
        newTaskId = newTaskId + 1;
        return newTaskId;
    }
}
