package com.codesoom.assignment.controllers;


import com.codesoom.assignment.services.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TodoController {

    private final TodoService todoService = new TodoService();

    @GetMapping("")
    public String list() {
        return "";
    }

    @PostMapping("")
    public String create() {
        return "";
    }

    @GetMapping("/{userId}")
    public String findOne(@PathVariable("userId") int id) {

        return "";
    }

    @PutMapping("/{userId}")
    public String update(@PathVariable("userId") int id) {

        return "updateTest";
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable("userId") int id) {

        return "";
    }
}
