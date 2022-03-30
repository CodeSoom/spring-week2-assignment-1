// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 2. Create - POST /tasks
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.common.StatusCodes;
import com.codesoom.assignment.common.RestResponse;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private TodoService todoService = new TodoService();

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
