package com.codesoom.assignment.controllers;

// TODO
// 1. GET /tasks (done)
// 2. GET /tasks/{id} (done)
//  -> 없을 경우 not found 처리
// 3. POST /tasks (done)
// 4. PUT/PATCH /tasks/{id} (done)
//  -> PUT은 어떻게 하지? PUT과 PATCH mapping을 같이 쓸 순 없나?
// 5. DELETE /tasks/{id} (done)
// 6. taskService를 만들기 (done)

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.codesoom.assignment.services.TaskService;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    TaskService taskService = new TaskService();

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

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source) {
        return taskService.updateTask(id, source);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
