package com.codesoom.assignment.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {

    private int code;
    private String msg;
    private T data;

    public void setSuccess(HttpStatus status, T data) {
        this.code = status.value();
        this.msg = "";
        this.data = data;
    }

    public void setFailed(ErrorCodes errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
