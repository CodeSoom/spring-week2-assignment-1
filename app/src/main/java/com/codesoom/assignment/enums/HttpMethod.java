package com.codesoom.assignment.enums;

public enum HttpMethod {
    GET("조회 요청"),
    POST("추가 요청"),
    PUT("수정 요청"),
    DELETE("삭제 요청");

    private final String description;

    HttpMethod(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
