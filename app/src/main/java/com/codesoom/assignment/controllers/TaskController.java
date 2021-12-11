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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private static Map<Long, Task> tasks = new LinkedHashMap<>();
    private static Long taskId = 0L;

    @GetMapping
    public List<Task> list() {
        return new ArrayList<>(tasks.values());
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.put(taskId, task);
        return task;
    }

    @GetMapping("/{id}")
    public Task detail(@PathVariable Long id, HttpServletResponse response) {
        Optional<Task> find = findTaskFromList(id);

        if (find.isEmpty()) {
            response.setStatus(400);
            return null;
        }

        return find.get();
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source, HttpServletResponse response) {
        Optional<Task> find = findTaskFromList(id);

        if (find.isEmpty()) {
            response.setStatus(400);
            return null;
        }

        Task task = find.get();
        task.setTitle(source.getTitle());
        return task;
    }

    @PutMapping("/{id}")
    public Task update2(@PathVariable Long id, @RequestBody Task source, HttpServletResponse response) {
        return update(id, source, response);
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable Long id, HttpServletResponse response) {
        Optional<Task> find = findTaskFromList(id);

        if (find.isEmpty()) {
            response.setStatus(400);
            return null;
        }

        tasks.remove(id);
        return find.get();
    }

    private Optional<Task> findTaskFromList(Long id) {
        Task task = tasks.get(id);
        return Optional.ofNullable(task);
    }

    private static synchronized Long generateId() {
        taskId += 1;
        return taskId;
    }
}
