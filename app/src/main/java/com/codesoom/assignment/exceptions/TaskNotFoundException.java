package com.codesoom.assignment.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public static final String DEFAULT_MESSAGE_FORMAT = "아이디가 [%d]인 할 일을 찾을 수 없습니다.";
    public static final String DEFAULT_MESSAGE = "할 일을 찾을 수 없습니다.";

    public TaskNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(Long id) {
        super(String.format(DEFAULT_MESSAGE_FORMAT, id));
    }

}
