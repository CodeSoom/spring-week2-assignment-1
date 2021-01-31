/*
REST API
ToDo 목록 얻기 - GET /tasks
ToDo 상세 조회하기 - GET /tasks/{id}
ToDo 생성하기 - POST /tasks
ToDo 제목 수정하기 - PUT/PATCH /tasks/{id}
ToDo 삭제하기 - DELETE /tasks/{id}
프론트엔드
할 일 조회하기
할 일 추가하기
할 일 수정하기
할 일 완료하기

 */



package com.codesoom.assignment.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String sayHello() {
        return "Hello, world!!!!";
    }
}
