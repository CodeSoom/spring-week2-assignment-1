package com.codesoom.assignment.domain.controllers;

import com.codesoom.assignment.common.util.TaskMapper;
import com.codesoom.assignment.domain.dtos.TaskDTO;
import com.codesoom.assignment.domain.entity.Task;
import com.codesoom.assignment.domain.service.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final TaskManager taskManager;

    TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return this.taskManager.getAllTask().stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public TaskDTO getTask(@PathParam("id") Long id) {
        Optional<Task> task = this.taskManager.getTask(id);
        if (!task.isPresent()) {
            return null; // 404 response
        }

        return TaskMapper.toDTO(task.get());
    }

    @PostMapping()
    public TaskDTO registerTask(@RequestBody TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        return TaskMapper.toDTO(this.taskManager.register(task));
    }

    @PutMapping("{id}")
    @PatchMapping("{id}")
    public TaskDTO modifyTask(@PathParam("id") Long id) {
        return TaskMapper.toDTO(this.taskManager.modifyTask(id));
    }


    @DeleteMapping("{id}")
    public TaskDTO deleteTask(@PathParam("id") Long id) {
        return TaskMapper.toDTO(this.taskManager.deleteTask(id));
    }

}
