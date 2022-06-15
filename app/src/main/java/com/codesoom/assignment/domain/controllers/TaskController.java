package com.codesoom.assignment.domain.controllers;

import com.codesoom.assignment.domain.dtos.TaskDTO;
import com.codesoom.assignment.domain.entity.Task;
import com.codesoom.assignment.domain.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
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
        return this.taskService.getAllTask().stream()
                .map(TaskDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public TaskDTO getTask(@PathParam("id") Long id) {
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
    public TaskDTO modifyTask(@PathParam("id") Long id, @RequestBody TaskDTO taskDTO) {
        Task modifiedTask = this.taskService.modifyTask(id, taskDTO.getTitle());

        return TaskDTO.from(modifiedTask);
    }


    @DeleteMapping("{id}")
    public TaskDTO deleteTask(@PathParam("id") Long id) {
        Task deletedTask = this.taskService.deleteTask(id);
        
        return TaskDTO.from(deletedTask);
    }

}
