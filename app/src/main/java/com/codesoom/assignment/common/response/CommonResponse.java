package com.codesoom.assignment.common.response;

import com.codesoom.assignment.common.Builder;
import com.codesoom.assignment.common.response.messages.error.ErrorCode;

public class CommonResponse {
    private Result result;
    private Object data;
    private String message;
    private String errorCode;

    public static <T> CommonResponse success(T data, String message) {
        return new CommonResponseBuilder().result(Result.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }

    public static <T> CommonResponse success(T data) {
        return success(data, null);
    }

    public static <T> CommonResponse success(String message) {
        return success(null, message);
    }

    public static CommonResponse fail(String message, String errorCode) {
        return new CommonResponseBuilder().result(Result.FAIL)
                .message(message)
                .errorCode(errorCode)
                .build();
    }

    public static CommonResponse fail(ErrorCode errorCode) {

        return new CommonResponseBuilder().result(Result.FAIL)
                .message(errorCode.getErrorMsg())
                .errorCode(errorCode.name())
                .build();
    }

    public static CommonResponse fail(String errorCode) {
        return new CommonResponseBuilder().result(Result.FAIL)
                .errorCode(errorCode)
                .build();
    }

    public enum Result {
        SUCCESS, FAIL
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Result getResult() {
        return result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    private CommonResponse(CommonResponseBuilder commonResponseBuilder) {
        this.result = commonResponseBuilder.result;
        this.data = commonResponseBuilder.data;
        this.message = commonResponseBuilder.message;
        this.errorCode = commonResponseBuilder.errorCode;
    }

    public static class CommonResponseBuilder implements Builder<CommonResponse> {
        private Result result;
        private Object data;
        private String message;
        private String errorCode;

        private CommonResponseBuilder() {
        }

        public CommonResponseBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public CommonResponseBuilder result(Result result) {
            this.result = result;
            return this;
        }

        public CommonResponseBuilder message(String message) {
            this.message = message;
            return this;

        }

        public CommonResponseBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        @Override
        public CommonResponse build() {
            return new CommonResponse(this);
        }
    }


}
