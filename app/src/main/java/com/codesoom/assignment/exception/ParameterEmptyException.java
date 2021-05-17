package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParameterEmptyException extends RuntimeException {
    public ParameterEmptyException(Long id, String title) {
        super(String.format("ID[%s] or Title[%s] is null or blank", id, title));
    }
}
