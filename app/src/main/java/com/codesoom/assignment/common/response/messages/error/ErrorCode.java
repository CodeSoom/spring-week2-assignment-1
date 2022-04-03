package com.codesoom.assignment.common.response.messages.error;

public enum ErrorCode {
    TITLE_IS_EMPTY("TITLE_IS_EMPTY"),
    TASK_ID_EMPTY("TASK_ID_EMPTY");

    private final String errorMsg;

    ErrorCode(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
