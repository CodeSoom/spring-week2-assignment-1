package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalControllerAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = TaskNotFoundException.class)
  public TaskNotFoundException notFoundException() {

    return new TaskNotFoundException("not found with that id");
  }
}
