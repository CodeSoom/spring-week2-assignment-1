package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.User;
import com.codesoom.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class UserController {
    private final UserService authorService;

    @Autowired
    public UserController(UserService authorService) {
        this.authorService = authorService;
    }

    /**
     * 제공된 이름을 가진 유저를 만들어 리턴합니다.
     *
     * @param name 유저명
     * @return 유저
     */
    @PostMapping
    public User createAuthor(@RequestBody String name) {
        return authorService.create(name);
    }
}
