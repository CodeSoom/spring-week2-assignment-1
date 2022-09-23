package com.codesoom.assignment.exception;

import com.codesoom.assignment.type.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

}
