package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller Advice
 * Controller에서 발생한 특정 Exception에 대한 Handler 클래스.
 * NoDataException 이 발생할 경우 HttpStatus.NOT_FOUND(404)에러를 리턴함.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmailDuplicateException(NoDataException e){
        return "존재하지 않는 ID 입니다. TO-DO List에 등록된 ID인지 확인해주세요.";
    }
}
