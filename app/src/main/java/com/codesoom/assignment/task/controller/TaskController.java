package com.codesoom.assignment.task.controller;

import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.domain.request.TaskRequestDto;
import com.codesoom.assignment.task.domain.response.TaskResponseDto;
import com.codesoom.assignment.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getTasks() {
        List<Task> tasks = taskService.getTasks();

        return ResponseEntity.status(HttpStatus.OK)
                .body(tasks.stream()
                        .map(TaskResponseDto::from)
                        .collect(Collectors.toList())
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable @Min(0) Long id) {
        Task task = taskService.getTaskById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(TaskResponseDto.from(task));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody @Valid TaskRequestDto taskRequestDto) {
        Task task = taskService.createTask(taskRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TaskResponseDto.from(task));
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable @Min(0) Long id,
                                                      @RequestBody @Valid TaskRequestDto taskRequestDto) {
        Task task = taskService.updateTask(id, taskRequestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(TaskResponseDto.from(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable @Min(0) Long id) {
        taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
