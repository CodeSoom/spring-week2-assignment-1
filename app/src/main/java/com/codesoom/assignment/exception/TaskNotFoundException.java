package com.codesoom.assignment.exception;

import com.codesoom.assignment.enums.ExceptionMessageType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "해당하는 Task가 존재하지 않습니다.";
    private static final String MESSAGE_FORMAT = " Id %d에 해당하는 Task가 존재하지 않습니다.";

    public TaskNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TaskNotFoundException(Long id, ExceptionMessageType messageType) {
        this(messageType.message() + String.format(MESSAGE_FORMAT, id));
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
