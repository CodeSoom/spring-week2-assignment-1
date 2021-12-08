package com.codesoom.assignment.controllers;

// TODO
// 1. GET /tasks (done)
// 2. GET /tasks/{id}
// 3. POST /tasks (done)
// 4. PUT/PATCH /tasks/{id} (done)
//  -> PUT은 어떻게 하지? PUT과 PATCH mapping을 같이 쓸 순 없나?
// 5. DELETE /tasks/{id} (done)

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source) {
        Task task = findTask(id);
        task.setTitle(source.getTitle());
        return task;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Task task = findTask(id);
        tasks.remove(task);
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


}
