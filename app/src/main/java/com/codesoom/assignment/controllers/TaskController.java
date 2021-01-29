package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.JsonTask;
import com.codesoom.assignment.application.TaskApplicationService;
import com.codesoom.assignment.application.TaskJsonTransfer;
import com.codesoom.assignment.domain.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    TaskApplicationService taskApplicationService = new TaskApplicationService();
    TaskJsonTransfer transfer = new TaskJsonTransfer();

    @GetMapping
    public String getAllTasks() throws UnknownException {
        return transfer.taskListToJson(taskApplicationService.getAllTasks())
            .orElseThrow(UnknownException::new);
    }

    @GetMapping("/{id}")
    public String getSpecificTask(@PathVariable Long id) throws NotFoundTaskException {
        return taskApplicationService.findTask(id)
            .flatMap(
                it -> transfer.taskToJson(it)
            ).orElseThrow(
                () -> new NotFoundTaskException(id)
            );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody JsonTask jsonTask) throws NotFoundTaskException, UnknownException {
        Long createdTaskId = taskApplicationService.createTask(jsonTask.title);
        Task createdTask = taskApplicationService.findTask(createdTaskId)
            .orElseThrow(
                () -> new NotFoundTaskException(createdTaskId)
            );
        return transfer.taskToJson(createdTask)
            .orElseThrow(UnknownException::new);
    }

    @PutMapping("/{id}")
    public String updateTitle(@PathVariable Long id, @RequestBody JsonTask jsonTask) throws NotFoundTaskException, UnknownException {
        taskApplicationService.updateTaskTitle(id, jsonTask.title).orElseThrow(
            () -> new NotFoundTaskException(id)
        );
        Task updatedTask = taskApplicationService.findTask(id)
            .orElseThrow(
                () -> new NotFoundTaskException(id)
            );
        return transfer.taskToJson(updatedTask)
                .orElseThrow(UnknownException::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) throws NotFoundTaskException {
        taskApplicationService.deleteTask(id).orElseThrow(
            () -> new NotFoundTaskException(id)
        );
    }
}
