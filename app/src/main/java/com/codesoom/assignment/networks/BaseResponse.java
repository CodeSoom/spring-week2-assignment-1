package com.codesoom.assignment.networks;

import org.springframework.http.HttpStatus;

public class BaseResponse<T> {

    private final HttpStatus status;

    private final T body;

    public BaseResponse(HttpStatus status) {
        this(status, null);
    }

    public BaseResponse(HttpStatus status, T body) {
        this.status = status;
        this.body = body;
    }

    public int getStatusCode() {
        return status.value();
    }

    public String getStatusMessage() {
        return status.getReasonPhrase();
    }

    public T getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", body=" + body +
                '}';
    }
}
