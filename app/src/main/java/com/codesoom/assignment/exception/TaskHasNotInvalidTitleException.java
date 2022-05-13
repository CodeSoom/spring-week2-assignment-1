package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;

public final class TaskHasNotInvalidTitleException extends TaskException {
    public TaskHasNotInvalidTitleException() {
        super(HttpStatus.BAD_REQUEST, "할 일의 제목을 확인해주세요");
    }
}
