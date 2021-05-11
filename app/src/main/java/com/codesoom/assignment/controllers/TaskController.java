package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
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

import java.util.ArrayList;
import java.util.List;

// TODO
// 1. PUT과 PATCH를 하나의 메서드에 맵핑시킬 수 있는지 확인할 것.
// 2. POST시 requestBody valid 체크. requestBody에 대한 이해가 필요하다..
// 3. tasks와 id를 컨트롤러가 가지고 있으니 이상하다.. tasks에 id를 넣어서 객체화시킬 수 있을까 흠.. 고민해보자.

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long currentTaskId = 0L;

    @GetMapping
    public List<Task> getTaskList() {
        return tasks;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable long id) {
        return findTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task param) {
        Task task = findTask(id);
        task.setTitle(param.getTitle());
        return task;
    }

    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable long id, @RequestBody Task param) {
        Task task = findTask(id);
        task.setTitle(param.getTitle());
        return task;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Task deleteTask(@PathVariable long id) {
        Task task = findTask(id);
        tasks.remove(task);
        return task;
    }

    private synchronized long generateId() {
        return this.currentTaskId += 1;
    }

    private Task findTask(long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException());
    }
}
