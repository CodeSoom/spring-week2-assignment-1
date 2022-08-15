package com.codesoom.assignment.exceptionAdvices;

import com.codesoom.assignment.controllers.TaskController;
import com.codesoom.assignment.exceptions.DataNotFoundException;
import com.codesoom.assignment.models.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = TaskController.class)
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResult dataNotFoundException(DataNotFoundException e){
        return new ErrorResult("ID에 해당하는 정보가 없습니다. [id] : " + e.getMessage());
    }
}
