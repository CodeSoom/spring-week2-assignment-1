package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return taskRepository.getTasks();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) throws IOException {
        return taskRepository.findTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        taskRepository.getTasks().add(task);

        return task;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task newTask) throws IOException {
        Task task = taskRepository.findTask(id);
        task.update(newTask);

        return task;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws IOException {
        Task task = taskRepository.findTask(id);
        taskRepository.removeTask(task);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
