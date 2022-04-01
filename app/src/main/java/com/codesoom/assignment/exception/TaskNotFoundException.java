package com.codesoom.assignment.exception;

import com.codesoom.assignment.models.Task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Task객체를 찾지 못했을때 StatusCode 가 404인 에러를 발생시킵니다.
 * @see ResponseStatus
 * @see HttpStatus
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super();
    }

    /**
     * 주어진 ID로 Task 객체를 찾지 못했을때 에러메세지를 던집니다.
     * @param id {@link Task} 객체의 ID
     */
    public TaskNotFoundException(Long id) {
        super("ID [" + id + "] 에 해당하는 Task를 찾을수 없습니다");
    }

    /**
     * 주어진 HttpMethod 에서 Task 객체에 접근할때 어떤 id로 찾지 못했을때 에러메세지를 던집니다.
     * @param id {@link Task} 객체의 ID
     * @param method {@link org.springframework.http.HttpMethod} 인 GET, POST, PUT, PATCH, DELETE 를 받습니다
     * @throws TaskNotFoundException
     */
    public TaskNotFoundException(Long id, String method) {
        super("ID [" + id + "] 에 해당하는 Task를 찾을수 없어, Task를 " + method + "할 수 없습니다");
    }
}
