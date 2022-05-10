package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> getTaskList() {
        return tasks;
    }

    @GetMapping("{id}")
    public Object getTaskItem(@PathVariable("id") Long id, HttpServletResponse response) {
        Task task = findTask(id);

        if (task == null) {
            response.setStatus(404);
            return "";
        }

        return task;
    }

    @PostMapping
    public Object createTask(@RequestBody Task task, HttpServletResponse response) {
        if(task.getTitle().isBlank()) {
            response.setStatus(400);
            return "";
        }
        else {
            task.setId(generateId());
            tasks.add(task);
            response.setStatus(201);
            return task;
        }
    }

    @PutMapping("/{id}")
    public Object updateTaskByPutMethod(@PathVariable("id") Long id,
                                        @RequestBody Task source, HttpServletResponse response) {
        return handleUpdate(id, source, response);
    }

    @PatchMapping("/{id}")
    public Object updateTaskByPatchMethod(@PathVariable("id") Long id,
                                          @RequestBody Task source, HttpServletResponse response) {
        return handleUpdate(id, source, response);
    }

    @DeleteMapping(value = {"", "/", "/{id}"})
    public String deleteTask(@PathVariable(required = false) Long id, HttpServletResponse response) {
        if(id == null) {
            response.setStatus(404);
            return "";
        }

        Task task = findTask(id);

        if(task == null) {
            response.setStatus(404);
            return "";
        }

        response.setStatus(204);
        tasks.remove(task);
        return "";
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    private Object handleUpdate(Long id, Task source, HttpServletResponse response) {
        if(source.getTitle().isBlank()) {
            response.setStatus(400);
            return "";
        }

        Task task = findTask(id);

        if (task == null) {
            response.setStatus(404);
            return "";
        }

        task.setTitle(source.getTitle());
        return task;
    }
}
