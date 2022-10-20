package com.codesoom.assignment.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "데이터가 존재하지 않습니다"),
    CONFLICT(HttpStatus.CONFLICT, "정책으로 인해 요청이 실패하였습니다"),
    PROCESSING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류로 인해 요청이 실패하였습니다");

    private final HttpStatus httpStatus;
    private final String detail;

    ErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }
}
