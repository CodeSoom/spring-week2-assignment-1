package com.codesoom.assignment.controllers;

import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping("")
    public String list() {
        return "LIST";
    }

    @GetMapping("/{id}")
    public String lists(@PathVariable Long id) {
        return "LISTS";
    }

    @PostMapping
    public String create(@RequestBody Task task) {
        return "Created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "Deleted";
    }

    @PutMapping("/{id}")
    public String putUpdate(@PathVariable Long id, @RequestBody Task task) {
        return "Updated";
    }

    @PatchMapping("/{id}")
    public String patchUpdate(@PathVariable Long id, @RequestBody Task task) {
        return "Updated";
    }
}
