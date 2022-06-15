package com.codesoom.assignment.domain.controllers;

import com.codesoom.assignment.domain.dtos.TaskDTO;
import com.codesoom.assignment.domain.entity.Task;
import com.codesoom.assignment.domain.service.TaskService;
import org.springframework.http.ResponseEntity;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = this.taskService.getAllTask();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>();
        }

        return tasks.stream()
                .map(TaskDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public TaskDTO getTask(@PathVariable Long id) {
        Optional<Task> task = this.taskService.getTask(id);
        if (!task.isPresent()) {
            return null; // 404 response
        }

        return TaskDTO.from(task.get());
    }

    @PostMapping()
    public TaskDTO registerTask(@RequestBody TaskDTO taskDTO) {
        Task task = Task.from(taskDTO);
        Task registeredTask = this.taskService.register(task);

        return TaskDTO.from(registeredTask);
    }

    @PutMapping("{id}")
    @PatchMapping("{id}")
    public TaskDTO modifyTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Optional<Task> modifiedTask = this.taskService.modifyTask(id, taskDTO.getTitle());
        if (!modifiedTask.isPresent()) {
            return null;
        }

        return TaskDTO.from(modifiedTask.get());
    }


    @DeleteMapping("{id}")
    public TaskDTO deleteTask(@PathVariable Long id) {
        Optional<Task> deletedTask = this.taskService.deleteTask(id);
        if (!deletedTask.isPresent()) {
            return null;
        }

        return TaskDTO.from(deletedTask.get());
    }

}
