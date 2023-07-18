package com.codesoom.assignment.models.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 에러 반환 응답 객체
 */
public class ErrorResponse {
    private String code;
    private String message;
    private Map<String, String> validation;

    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    public void addValidation(String field, String message) {
        validation.put(field, message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getValidation() {
        return validation;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setValidation(Map<String, String> validation) {
        this.validation = validation;
    }

    @Override
    public String toString() {
        return String.format("code: %s, message: %s, validation: %s", code, message, validation);
    }
}
