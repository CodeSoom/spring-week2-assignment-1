

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;


    @GetMapping
    public List<Task> list() {
        return tasks;
    }


    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generatedId());
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public List<Task> delete(@PathVariable Long id) {

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.remove(i);
            }
        }
        return tasks;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Task task) {

        for (Task value : tasks) {
            if (value.getId().equals(id)) {
                value.setTitle(task.getTitle());
            }
        }
        return "Update ok";
    }


    private Long generatedId() {

        newId += 1;
        return newId;
    }



}
