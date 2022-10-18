package com.codesoom.assignment.task.controller;

import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.domain.request.TaskRequestDto;
import com.codesoom.assignment.task.domain.response.TaskResponseDto;
import com.codesoom.assignment.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/*
    1. Read Collection - GET /tasks => 완료
    2. Read Item - GET /tasks/{id}  => 완료
    3. Create - POST /tasks => 완료
    4. Update - PUT/PATCH /tasks/{id}
    5. Delete - DELETE /tasks/{id}
*/

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> gets() {
        List<Task> tasks = taskService.getTasks();

        return ResponseEntity.ok().body(
                tasks.stream()
                        .map(TaskResponseDto::from)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> get(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);

        return ResponseEntity.ok().body(TaskResponseDto.from(task));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskService.createTask(taskRequestDto);

        return ResponseEntity.ok().body(TaskResponseDto.from(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> put(@PathVariable Long id,
                                               @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskService.updateTask(id, taskRequestDto);

        return ResponseEntity.ok().body(TaskResponseDto.from(task));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> patch(@PathVariable Long id,
                                                 @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskService.updateTask(id, taskRequestDto);

        return ResponseEntity.ok().body(TaskResponseDto.from(task));
    }
}
