package com.codesoom.assignment.controllers;

// DONE: GET /tasks - 모든 Task 조회
// DONE: GET /tasks/{id} - 1개의 Task 조회
// DONE: POST /tasks - Task 1개 추가
// DONE: PUT/PATCH /tasks/{id} - Task 수정
// DONE: DELETE /tasks/{id} - 할 일 삭제

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.dtos.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
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

    @GetMapping("/{id}")
    public Task findTask(@PathVariable long id) {
        return taskRepository.find(id);
    }

    @GetMapping("")
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody TaskDto taskDto) { return taskRepository.update(id, taskDto.getTitle()); }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.remove(id);
    }
}
