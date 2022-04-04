package com.codesoom.assignment.models;

/**
 *  통일된 에러 응답을 위한 객체입니다.
 */
public class ErrorResponse {

    private final String code;
    private final String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
