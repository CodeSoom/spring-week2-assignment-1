package com.codesoom.assignment.exception;

import com.codesoom.assignment.type.ErrorCode;
import lombok.*;

@Getter
@Setter
public class TaskException extends Throwable {
    private ErrorCode errorCode;
    private String message;

}
