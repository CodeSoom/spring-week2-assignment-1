package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskEditDto;
import com.codesoom.assignment.dto.TaskSaveDto;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> list() {
        List<TaskDto> tasks = taskService.getTaskDtoList();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskDto> save(@RequestBody TaskSaveDto taskSaveDto) {
        TaskDto taskDto = taskService.save(taskSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDto);
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

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> replace(@PathVariable Long taskId, @RequestBody TaskEditDto taskEditDto) {

        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = findTask.get();
        taskService.replaceTask(task, taskEditDto);

        TaskDto taskDto = TaskDto.from(task);
        return ResponseEntity.ok().body(taskDto);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDto> modify(@PathVariable Long taskId, @RequestBody TaskEditDto taskEditDto) {

        Optional<Task> findTask = taskService.getTask(taskId);
        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = findTask.get();
        taskService.modifyTask(task, taskEditDto);

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