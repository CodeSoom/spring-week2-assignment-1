package com.codesoom.assignment.exceptionAdvices;

import com.codesoom.assignment.controllers.TaskController;
import com.codesoom.assignment.exceptions.DataNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = TaskController.class)
public class exceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ResponseEntity<String> dataNotFoundException(DataNotFoundException e){
        return new ResponseEntity<>("ID에 해당하는 정보가 없습니다." , HttpStatus.NOT_FOUND);
    }
}
