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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public ResponseEntity getTaskById(@PathVariable Long id) {
        Task task = todoService.findTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskDto taskDto) {
        Task task = todoService.saveTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {

        Task updatedTask = todoService.updateTask(id, taskDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        todoService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
