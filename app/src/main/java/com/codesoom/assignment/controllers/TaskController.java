package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.ITaskService;
import com.codesoom.assignment.application.impl.TaskService;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskRequest;
import com.codesoom.assignment.models.Task;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

    private final ITaskService taskService = new TaskService();

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(
        @PathVariable("taskId") @Valid Long taskId
    ) {
        return taskService.getTask(taskId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(
        @Valid TaskRequest reqDto
    ) {
        return taskService.createTask(reqDto.getTitle());
    }

    @PutMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(
        @PathVariable("taskId") @Valid Long taskId,
        @Valid TaskRequest reqDto
    ) {
        Task oldTask = taskService.getTask(taskId);

        if (Objects.isNull(oldTask)) {
            throw new IllegalArgumentException("There is no task with id " + taskId);
        }

        TaskDto taskDto = TaskDto.from(null, reqDto.getTitle());
        Task newTask = Task.from(taskDto);
        return taskService.updateTask(taskId, newTask);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(
        @PathVariable("taskId") @Valid Long taskId
    ) {
        Task oldTask = taskService.getTask(taskId);

        if (Objects.isNull(oldTask)) {
            throw new IllegalArgumentException("There is no task with id " + taskId);
        }

        taskService.deleteTask(taskId);
    }
}
