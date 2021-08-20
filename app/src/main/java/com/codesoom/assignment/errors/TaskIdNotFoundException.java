package com.codesoom.assignment.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskIdNotFoundException extends RuntimeException {

    public TaskIdNotFoundException(Long id) {
        super(String.format("id %s 에 해당하는 할 일을 찾을 수 없습니다.", id));
    }
}
