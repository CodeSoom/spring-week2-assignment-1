package com.codesoom.assignment.controllers;

import com.codesoom.assignment.interfaces.DefaultRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RequestParamValidation {
    private final Long pathVariable;
    private final DefaultRepository repository;

    public RequestParamValidation(Long pathVariable, DefaultRepository repository) {
        this.pathVariable = pathVariable;
        this.repository = repository;
    }


    public void validate() {
        if (repository.notPresent(pathVariable)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Id로 해당 Task를 찾을 수 없습니다");
        }
    }
}
