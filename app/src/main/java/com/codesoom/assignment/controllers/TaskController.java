package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO
// 1. Read Collection 목록 얻기 - GET /tasks
// 2. Read Item 조회하기 - GET /tasks/{id}
// 3. Create 생성하기 - POST /tasks
// 4. Update 제목 수정하기 - PUT/PATCH /tasks/{id}
// 5. Delete 삭제하기 - DELETE /tasks/{id}

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private TaskService taskService;

    public TaskController() {
        taskService = new TaskService();
    }

    @GetMapping
    public List<Task> list() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task detail(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source) {
        return taskService.updateTask(id, source);
    }

    @PatchMapping("/{id}")
    public Task patch(@PathVariable Long id, @RequestBody Task source) {
        return taskService.updateTask(id, source);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }
}

