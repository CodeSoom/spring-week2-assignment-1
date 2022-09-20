package com.codesoom.assignment.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    TASK_NULL(404, "존재하지 않는 id입니다"),
    ID_NOT_FOUND(404, "id를 입력해주세요");

    private final int status;
    private final String message;
    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
