package com.codesoom.assignment.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public static final String DEFAULT_MESSAGE = "요소를 찾을 수 없습니다.";

    public EntityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
