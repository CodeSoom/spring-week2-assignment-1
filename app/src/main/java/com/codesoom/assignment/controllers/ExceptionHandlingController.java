package com.codesoom.assignment.controllers;

import com.codesoom.assignment.InternalServerError;
import com.codesoom.assignment.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalControllerAdvice {

  @RequestMapping({"/{id}"})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = TaskNotFoundException.class)
  public TaskNotFoundException notFoundException(@PathVariable("id") Long id) {

    return new TaskNotFoundException("not found with that id : " +id);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = InternalServerError.class)
  public InternalServerError internalServerError(){

    return new InternalServerError("서버 내부에 잘못이 있습니다.");
  }
}
