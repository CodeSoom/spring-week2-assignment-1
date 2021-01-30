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
 * 할 일들의 예외처리를 위한 클래스
 *
 * @see TaskNotFoundException
 * @see MethodArgumentNotValidException
 * @see ErrorResponse
 */
@RestControllerAdvice
public class TaskAdvice {

    /**
     * TaskNotFoundException 이 발생하면 404코드와 에러정보를 리턴한다.
     *
     * @param exception - 할 일이 존재하지 않을때 발생하는 예외
     * @param request - 요청 정보
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
     * MethodArgumentNotValidException 이 발생하면 400코드와 에러정보를 리턴한다.
     *
     * @param exception - 인수가 유효하지 않을때 발생하는 예외
     * @param request - 요청 정보
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
