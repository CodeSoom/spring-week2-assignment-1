package com.codesoom.assignment.controllers;

// TODO: GET /tasks - 모든 Task 조회
// TODO: GET /tasks/{id} - 1개의 Task 조회
// TODO: POST /tasks - Task 1개 추가
// TODO: PUT/PATCH /tasks/{id} - Task 수정
// TODO: DELETE /tasks/{id} - 할 일 삭제

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.dtos.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task saveTask(@RequestBody TaskDto taskDto) {
        return taskRepository.save(taskDto);
    }
}
