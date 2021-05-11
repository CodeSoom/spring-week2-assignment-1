package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}
// +) PUT과 PATCH를 하나의 메서드에 맵핑시킬 수 있는지 확인할 것.

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
    public Task addTask(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable long id) {
        return "task id:" + id;
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable long id) {
        return "task id: " + id + " updated!";
    }

    @PatchMapping("/{id}")
    public String patchTask(@PathVariable long id) {
        return "task id: " + id + " patched!";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable long id) {
        return "task id: " + id + " deleted!";
    }

    private synchronized long generateId() {
        return this.currentTaskId += 1;
    }
}
