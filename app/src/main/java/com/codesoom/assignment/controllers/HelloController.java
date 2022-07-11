package com.codesoom.assignment.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloController {
    @RequestMapping("/")
    public String sayHello() {
        return "안녕하세요, 리뷰어님. 이번주도 잘 부탁드립니다.";
    }
}
