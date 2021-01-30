package com.codesoom.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TaskControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElement() {
        /**
         * detail에 대한 exception일 경우는 content type이 text/plain
         * update에 대한 exception일 경우는 content type이 application/json
         * 왜 차이가 발생하는지 알아봐야함
         */
        return "Not found task";
    }
}
