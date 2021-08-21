package com.codesoom.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 애플리케이션 전역에 공통으로 사용되는 Global Exception Handler 입니다.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 조회된 할 일이 없는 경우 발생하는 예외입니다.
     */
    @ResponseStatus(code=HttpStatus.NOT_FOUND, reason = "No Such Task Found.")
    @ExceptionHandler(TaskNotFoundException.class)
    public void NoSuchTaskFound() { }

    /**
     * 메소드 Argument 타입이 일치하지 않는 경우 발생하는 예외입니다.
     * 
     * @param e MethodArgumentTypeMismatchException 예외
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Method Argument Type Mismatch Exception")
    public void handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    }
}
