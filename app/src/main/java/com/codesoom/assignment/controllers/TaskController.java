package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.enums.ExceptionMessages;
import com.codesoom.assignment.networks.BaseResponse;
import com.codesoom.assignment.services.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public BaseResponse<List<Task>> readTasks() {
        return taskService.readTasks();
    }

    @GetMapping("/tasks/{taskId}")
    public BaseResponse<Task> readTask(@PathVariable Long taskId) {
        if (isInValidTaskId(taskId)) {
            throw new RuntimeException(ExceptionMessages.INVALID_TASK_ID.getMessage());
        }

        return taskService.readTask(taskId);
    }

    @PostMapping("/tasks")
    public BaseResponse<Task> addTask(@RequestBody Task newTask) {
        if (newTask == null) {
            throw new RuntimeException(ExceptionMessages.REQUEST_TASK_NOT_FOUND.getMessage());
        }

        if (!newTask.hasValidContent()) {
            throw new RuntimeException(ExceptionMessages.INVALID_TASK_CONTENT.getMessage());
        }

        return taskService.addTask(newTask);
    }

    @RequestMapping(value = "/tasks/{taskId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public BaseResponse<Task> editTask(@PathVariable Long taskId, @RequestBody Task task) {
        if (isInValidTaskId(taskId)) {
            throw new RuntimeException(ExceptionMessages.INVALID_TASK_ID.getMessage());
        }

        if (task == null) {
            throw new RuntimeException(ExceptionMessages.REQUEST_TASK_NOT_FOUND.getMessage());
        }

        if (!task.hasValidContent()) {
            throw new RuntimeException(ExceptionMessages.INVALID_TASK_CONTENT.getMessage());
        }

        return taskService.editTask(taskId, task);
    }

    @DeleteMapping("/tasks/{taskId}")
    public BaseResponse deleteTask(@PathVariable Long taskId) {
        if (isInValidTaskId(taskId)) {
            throw new RuntimeException(ExceptionMessages.INVALID_TASK_ID.getMessage());
        }

        return taskService.deleteTask(taskId);
    }

    private boolean isInValidTaskId(Long taskId) {
        return taskId <= 0;
    }

}
