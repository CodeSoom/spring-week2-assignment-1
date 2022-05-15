package com.codesoom.assignment.controller;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskRequestDto;
import com.codesoom.assignment.dto.TaskResponseDto;
import com.codesoom.assignment.exception.TaskInvalidTitleException;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDto findOne(@PathVariable Long id) {
        return toTaskResponseDto(taskService.findOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponseDto> findAll() {
        return taskService.findAll().stream()
                .map(Task::toTaskResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto create(@RequestBody TaskRequestDto taskDto) {
        Task task = taskDto.toTask();
        if (task.hasNotTitle()) {
            throw new TaskInvalidTitleException();
        }

        return toTaskResponseDto(taskService.create(task));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDto put(@PathVariable Long id, @RequestBody TaskRequestDto taskDto) {
        return toTaskResponseDto(update(id, taskDto.toTask()));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDto patch(@PathVariable Long id, @RequestBody TaskRequestDto taskDto) {
        return toTaskResponseDto(update(id, taskDto.toTask()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    /**
     * PUT/PATCH는 현재 동일한 동작으로 처리하기 위해 로직 공통화
     * @param id
     * @param task
     * @return
     */
    public Task update(Long id, Task task) {
        if (task.hasNotTitle()) {
            throw new TaskInvalidTitleException();
        }

        return taskService.update(id, task);
    }

    public TaskResponseDto toTaskResponseDto(Task task) {
        return task.toTaskResponseDto();
    }
}
