package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long id = 0L;

    /**
     * GET /tasks
     * @return tasks
     */
    @GetMapping
    public List<Task> getLists() {
        return tasks;
    }

    /**
     * GET /tasks/{id}
     * @param id
     * @return task
     */
    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * POST /tasks
     * @param task
     * @return task
     */
    @PostMapping
    public Task createList(@RequestBody Task task) {
        task.setTitle(task.getTitle());
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    /**
     * PUT/PATCH /tasks/{id}
     * @param id
     * @param task
     * @return task
     */
    @PutMapping("/{id}")
    public Task putTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {

        Task filteredTask = tasks.stream()
                            .filter(t -> t.getId().equals(id))
                            .findFirst()
                            .orElse(null);
        filteredTask.setTitle(task.getTitle());

        return filteredTask;
    }

    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {

        Task filteredTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        filteredTask.setTitle(task.getTitle());

        return filteredTask;
    }

    /**
     * DELETE /tasks/{id}
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") Long id) {
        Task filteredTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);

        tasks.remove(filteredTask);
    }

    /**
     * id 1씩 증가
     * @return 1 증가된 id
     */
    private Long generateId() {
        id += 1;
        return id;
    }
}
