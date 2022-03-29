package com.codesoom.assignment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super();
    }
    public TaskNotFoundException(Long id) {
        super("ID [" + id + "] 에 해당하는 Task를 찾을수 없습니다");
    }

    public TaskNotFoundException(Long id, String method) {
        super("ID [" + id + "] 에 해당하는 Task를 찾을수 없어, Task를 " + method + "할 수 없습니다");
    }
}
