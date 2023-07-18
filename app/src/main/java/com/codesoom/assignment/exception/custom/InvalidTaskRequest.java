package com.codesoom.assignment.exception.custom;

import com.codesoom.assignment.exception.CustomBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Task Request")
public class InvalidTaskRequest extends CustomBaseException {
    private static final String MESSAGE = "할 일 생성 요청이 올바르지 않습니다.";

    public InvalidTaskRequest() {
        super(MESSAGE);
    }

    public InvalidTaskRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
