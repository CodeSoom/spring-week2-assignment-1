package com.codesoom.assignment.controllers;

import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import com.codesoom.assignment.interfaces.DefaultRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class RequestBodyValidation {
    private final TaskRequestDto body;

    public RequestBodyValidation(TaskRequestDto body) {
        this.body = body;
    }


    public void validate() {
        if (body.getTitle() == null || Objects.equals(body.getTitle(), "")
                || Objects.equals(body.getTitle(), " ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title에 유요한 값을 입려갷야 합니다");
        }
    }
}
