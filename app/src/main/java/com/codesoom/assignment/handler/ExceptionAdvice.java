package com.codesoom.assignment.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

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
    public ErrorHandler notReadableHandler(HttpMessageNotReadableException he) {
        return new ErrorHandler("Not Readable", "title이 존재하지 않습니다. 다시 한 번 확인해주세요.");
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorHandler methodNotSupportedHandler(HttpRequestMethodNotSupportedException he) {
        return new ErrorHandler("Method Not Supported", "Id를 입력해주세요.");
    }

}
