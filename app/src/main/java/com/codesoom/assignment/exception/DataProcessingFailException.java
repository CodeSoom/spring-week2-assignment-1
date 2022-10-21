package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;

/**
 * 데이터 처리를 실패할 때 던집니다.
 */
public class DataProcessingFailException extends CommonException {
    private static final String MESSAGE = "데이터 처리에 실패하였습니다. 잠시 후 다시 요청해주세요.";

    public DataProcessingFailException() {
        super(MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
