// TODO
// 1. Read - Collection => 완료
// 2. Read - Item => 완료
// 3. Create => 완료
// 4. Update => 완료
// 5. Delete => 완료

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static List<Task> tasks = new ArrayList<>();
    private static Long newId = 0L;

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

    @GetMapping("/{id}")
    public Task detail(@PathVariable Long id) {
        return toTask(id);
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source) {
        Task task = toTask(id);
        task.setTitle(source.getTitle());
        return task;
    }

    @PutMapping("/{id}")
    public Task update2(@PathVariable Long id, @RequestBody Task source) {
        return update(id, source);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Task task = toTask(id);
        tasks.remove(task);
    }

    private Task toTask(Long id) {
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
