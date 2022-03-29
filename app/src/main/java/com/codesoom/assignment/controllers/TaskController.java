// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 2. Create - POST /tasks
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.common.ErrorCodes;
import com.codesoom.assignment.common.RestResponse;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private TodoService todoService = new TodoService();

    // 스프링이 내부에서 jackson을 사용해서 json으로 보내줌
    @GetMapping
    public ResponseEntity<List<Task>> list() {

        List<Task> tasks = todoService.findAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Task>> getTaskById(@PathVariable Long id) {

        RestResponse response = new RestResponse();
        Optional<Task> findTask = todoService.findTaskById(id);

        if (findTask.isPresent()) {
            response.setSuccess(HttpStatus.OK, findTask.get());
        } else {
            response.setFailed(ErrorCodes.NOT_FOUND);
        }

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        todoService.saveTask(task);
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
