package com.codesoom.assignment.handler;

import com.codesoom.assignment.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice("com.codesoom.assignment")
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public ErrorHandler NPEHandler(NullPointerException ne) {
        return new ErrorHandler("Not Found", ne.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ErrorHandler BadRequestHandler(HttpClientErrorException.BadRequest badRequest) {
        return new ErrorHandler("Bad Request", "다시 한 번 확인해주세요.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorHandler notReadableExHandler(HttpMessageNotReadableException he) {
        return new ErrorHandler("Not Readable", "title이 존재하지 않습니다. 다시 한 번 확인해주세요.");
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorHandler methodNotSupportedExHandler(HttpRequestMethodNotSupportedException he) {
        return new ErrorHandler("Method Not Supported", "Id를 입력해주세요.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    public ErrorHandler taskNotFoundExHandler(TaskNotFoundException te) {
        return new ErrorHandler("Task Not Found", "Task가 존재하지 않습니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorHandler typeMismatchExHandler(MethodArgumentTypeMismatchException me) {
        return new ErrorHandler("Type Mismatch", "Id는 숫자만 입력 가능합니다.");
    }
}
