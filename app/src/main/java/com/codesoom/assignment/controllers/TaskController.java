package com.codesoom.assignment.controllers;
// 1. READ Collection - GET / tasks => 완료
// 2. READ Item - GET / tasks/{id} => 완료
// 3. CREATE POST/tasks => 완료
// 4. UPDATE PUT/PATCH/tasks/{id} => 완료
// 5. DELETE DELETE/tasks/{id} => 완료

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;


    // GET
    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    // GET tasks/id
    @GetMapping("/{id}")
    public Task getTask(@PathVariable(value = "id") Long id) {
        return selectTask(tasks, id);
    }

    // POST
    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    // UPDATE
    @PutMapping("/{id}")
    public Task update(@PathVariable(value = "id") Long id, @RequestBody Task task) {
        Task originTask = selectTask(tasks, id);
        if (originTask != null) {
            originTask.setTitle(task.getTitle());
            return originTask;
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        tasks.remove(selectTask(tasks, id));
    }

    private Task selectTask(List<Task> tasks, Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    private Long generateId(){
        newId += 1;
        return newId;
    }
}
