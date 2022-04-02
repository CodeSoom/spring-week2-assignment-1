package com.codesoom.assignment.advice;

import com.codesoom.assignment.controllers.TaskController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 할 일에 대한 HTTP 요청 중 예외에 대해 응답합니다.
 */
@RestControllerAdvice(basePackageClasses = TaskController.class)
public class TaskControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<Object> runtimeException(RuntimeException ex) {
        return ResponseEntity.notFound().build();
    }
}
