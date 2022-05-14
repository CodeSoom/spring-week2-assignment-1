package com.codesoom.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Task를 찾을 수 없습니다")
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }
}
