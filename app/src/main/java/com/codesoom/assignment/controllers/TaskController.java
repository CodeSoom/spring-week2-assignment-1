package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO
// 1. PUT과 PATCH를 하나의 메서드에 맵핑시킬 수 있는지 확인할 것.
// 2. POST시 requestBody valid 체크. requestBody에 대한 이해가 필요하다..

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    final private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTask() {
        return taskRepository.getAllTask();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        return taskRepository.addTask(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable long id) {
        return taskRepository.getTask(id);
    }

    //TODO: 메서드 이름 적절한지 여쭤볼 것
    @PutMapping("/{id}")
    public Task setAndReturnTask(@PathVariable long id, @RequestBody Task param) {
        return taskRepository.setAndReturnTask(id, param);
    }

    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable long id, @RequestBody Task param) {
        return taskRepository.patchTask(id, param);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Task deleteAndReturnTask(@PathVariable long id) {
        return taskRepository.deleteTask(id);
    }
}
