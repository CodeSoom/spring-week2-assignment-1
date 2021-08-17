package com.codesoom.assignment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalControllerAdvice {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity exception(Exception e) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}
