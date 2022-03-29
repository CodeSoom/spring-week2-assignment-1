package com.codesoom.assignment.common;

public enum ErrorCodes {

    BAD_REQUEST(400, "잘못된 요청입니다"),
    NOT_FOUND(404, "요청하신 Task가 존재하지 않습니다");

    private final int code;
    private final String msg;


    ErrorCodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
