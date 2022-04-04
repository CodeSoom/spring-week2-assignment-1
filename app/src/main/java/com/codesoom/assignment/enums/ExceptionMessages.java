package com.codesoom.assignment.enums;

public enum ExceptionMessages {

    INVALID_TASK_ID("task id 는 0보다 같거나 작을 수 없습니다."),
    INVALID_TASK_CONTENT("task 제목 형식이 올바르지 않습니다."),
    REQUEST_TASK_NOT_FOUND("수정 또는 변경하고자하는 내용을 찾을 수 없습니다."),
    TASK_NOT_FOUND("일치하는 task를 찾을 수 없습니다.");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
