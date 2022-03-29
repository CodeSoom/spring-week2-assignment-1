// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 2. Create - POST /tasks
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    // 스프링이 내부에서 jackson을 사용해서 json으로 보내줌
    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/{id}")
    public void getTaskById(@PathVariable Long id) {
        System.out.println(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(1L);
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public void updateTaskAll(@PathVariable Long id) {
        System.out.println(id);
    }

    @PatchMapping("/{id}")
    public void updateTask(@PathVariable Long id) {
        System.out.println(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        System.out.println(id);
    }
}
