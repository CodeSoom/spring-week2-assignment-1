package com.codesoom.assignment.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskIdNotFoundException extends RuntimeException {

    public TaskIdNotFoundException(String methodName, Long id) {
        super(String.format("%s 메서드에서 id %s 를 찾을 수 없습니다.", methodName, id));
    }
}
