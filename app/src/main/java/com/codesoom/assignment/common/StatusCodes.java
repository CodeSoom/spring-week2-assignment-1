package com.codesoom.assignment.common;

public enum StatusCodes {

    OK(200, ""),
    BAD_REQUEST(400, "잘못된 요청입니다"),
    NOT_FOUND(404, "요청한 정보 또는 리소스를 찾을 수 없습니다");

    private final int code;
    private final String msg;


    StatusCodes(int code, String msg) {
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
