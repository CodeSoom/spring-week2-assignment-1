package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskRepository taskRepository = new TaskRepository();

    @GetMapping
    public List<Task> list() {
        return taskRepository.findList();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskRepository.add(task);
    }

    @GetMapping("/{id}")
    public Task detail(@PathVariable Long id, HttpServletResponse response) {
        Optional<Task> find = taskRepository.findDetail(id);

        if (find.isEmpty()) {
            response.setStatus(400);
            return null;
        }

        return find.get();
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source, HttpServletResponse response) {
        Optional<Task> task = taskRepository.update(id, source.getTitle());

        if (task.isEmpty()) {
            response.setStatus(400);
            return null;
        }

        return task.get();
    }

    @PutMapping("/{id}")
    public Task update2(@PathVariable Long id, @RequestBody Task source, HttpServletResponse response) {
        return update(id, source, response);
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable Long id, HttpServletResponse response) {
        Optional<Task> find = taskRepository.findDetail(id);

        if (find.isEmpty()) {
            response.setStatus(400);
            return null;
        }

        taskRepository.delete(id);
        return find.get();
    }

}
