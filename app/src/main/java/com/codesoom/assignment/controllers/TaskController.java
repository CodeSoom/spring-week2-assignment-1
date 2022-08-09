package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.ITaskService;
import com.codesoom.assignment.application.impl.TaskService;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskReq;
import com.codesoom.assignment.models.Task;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
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
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(
        @Valid Task task
    ) {
        return taskService.createTask(task);
    }

    @PutMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(
        @PathVariable("taskId") @Valid Long taskId,
        @Valid TaskReq task
    ) {
        TaskDto taskDto = TaskDto.from(null, task.getTitle());
        Task newTask = Task.from(taskDto);
        return taskService.updateTask(taskId, newTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(
        @PathVariable("taskId") @Valid Long taskId
    ) {
        taskService.deleteTask(taskId);
    }
}
