package com.codesoom.assignment.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @GetMapping
    public String fetchTask() {
        return "List";
    }

    @GetMapping("/{id:[0-9]+}")
    public String fetchList() {
        return "Detail";
    }

    @PostMapping
    public String create(@RequestBody String text) {
        return "Created";
    }

    @RequestMapping(path = "/{id:[0-9]+}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public String update(@RequestBody String text) {
        return "Updated";
    }

    @DeleteMapping("/{id:[0-9]+}")
    public String delete() {
        return "Deleted";
    }
}
