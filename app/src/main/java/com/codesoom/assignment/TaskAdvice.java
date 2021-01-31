package com.codesoom.assignment;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * 할 일들의 예외처리를 담당합니다.
 *
 * @see TaskNotFoundException
 * @see MethodArgumentNotValidException
 * @see ErrorResponse
 */
@RestControllerAdvice
public class TaskAdvice {

    /**
     * 할 일을 찾을 수 없는 경우의 예외를 처리합니다.
     *
     * @param exception 할 일이 존재하지 않을 때 발생하는 예외
     * @param request 요청 정보
     * @return 404코드와 에러정보
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(TaskNotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * 인수가 유효하지 않는 경우의 예외를 처리합니다.
     *
     * @param exception 인수가 유효하지 않을 때 발생하는 예외
     * @param request 요청 정보
     * @return 400코드와 에러정보
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                      WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
