package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dtos.TaskDTO;
import com.codesoom.assignment.service.TaskManager;
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

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    TaskManager taskManager;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return this.taskManager.getAllTask();
    }

    @GetMapping("{id}")
    public TaskDTO getTask(@PathParam("id") Long id) {
        return this.taskManager.getTask(id);
    }

    @PostMapping()
    public TaskDTO registerTask(@RequestBody TaskDTO taskDTO) {
        return this.taskManager.register(taskDTO);
    }

    @PutMapping("{id}")
    @PatchMapping("{id}")
    public TaskDTO modifyTask(@PathParam("id") Long id) {
        return this.taskManager.modifyTask(id);
    }


    @DeleteMapping("{id}")
    public TaskDTO deleteTask(@PathParam("id") Long id) {
        return this.taskManager.deleteTask(id);
    }
}
