package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;

public final class TaskNotFoundException extends TaskException {
    public TaskNotFoundException() {
        super(HttpStatus.NOT_FOUND, "할 일을 찾을 수 없습니다. 입력하신 할 일의 id를 확인해주세요.");
    }
}
