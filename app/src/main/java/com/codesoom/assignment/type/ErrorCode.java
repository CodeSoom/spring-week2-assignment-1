package com.codesoom.assignment.type;

import lombok.Getter;

@Getter
public enum ErrorCode {
    TASK_NOT_FOUND("NO TASK IS FOUND");
    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }
}
