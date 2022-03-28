package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskSaveDto;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> list() {
        List<TaskDto> tasks = taskService.getTaskList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> view(@PathVariable Long taskId) {
        Optional<TaskDto> taskDto = taskService.getTask(taskId);
        if (taskDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(taskDto.get());
    }

    @PostMapping
    public ResponseEntity<TaskDto> save(@RequestBody TaskSaveDto taskSaveDto) {
        TaskDto taskDto = taskService.save(taskSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDto);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> delete(@PathVariable Long taskId) {

        boolean isDeleted = taskService.delete(taskId);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
