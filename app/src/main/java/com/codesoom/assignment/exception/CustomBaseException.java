package com.codesoom.assignment.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 사용자정의 예외의 추상클래스 특정 필드의 유효성 검증 실패 정보를 저장하고, HTTP 상태 코드를 반환합니다.
 */
public abstract class CustomBaseException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public CustomBaseException() {
    }

    public CustomBaseException(String message) {
        super(message);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
