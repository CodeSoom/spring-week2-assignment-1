package com.codesoom.assignment.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/tasks")
public class HelloController {
    @GetMapping("/{taskId}")
    public String getTask(@PathVariable Long taskId) {
        return "안녕하세요, 리뷰어님. 이번주도 잘 부탁드립니다.";
    }
}
