package com.codesoom.assignment.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    TASK_NOT_FOUND("NO USER IS FOUND");
    private final String description;
}
