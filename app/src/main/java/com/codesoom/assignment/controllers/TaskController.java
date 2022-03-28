package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
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
        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = findTask.get();
        TaskDto taskDto = TaskDto.from(task);
        return ResponseEntity.ok().body(taskDto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> save(@RequestBody TaskSaveDto taskSaveDto) {
        TaskDto taskDto = taskService.save(taskSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDto);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> replace(@PathVariable Long taskId, @RequestBody TaskSaveDto taskSaveDto) {

        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Task task = findTask.get();
        taskService.replaceTask(task, taskSaveDto);

        TaskDto taskDto = TaskDto.from(task);
        return ResponseEntity.ok().body(taskDto);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDto> modify(@PathVariable Long taskId, @RequestBody TaskSaveDto taskSaveDto) {

        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = findTask.get();
        taskService.replaceTask(task, taskSaveDto);

        TaskDto taskDto = TaskDto.from(task);
        return ResponseEntity.ok().body(taskDto);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> delete(@PathVariable Long taskId) {

        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        taskService.delete(taskId);

        return ResponseEntity.noContent().build();
    }
}